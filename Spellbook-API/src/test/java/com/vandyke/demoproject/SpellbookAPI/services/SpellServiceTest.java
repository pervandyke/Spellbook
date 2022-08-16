package com.vandyke.demoproject.SpellbookAPI.services;

import org.testng.annotations.Test;

import com.vandyke.demoproject.SpellbookAPI.exeptions.SpellNotFoundException;
import com.vandyke.demoproject.SpellbookAPI.exeptions.UserNotFoundException;
import com.vandyke.demoproject.SpellbookAPI.frontEndObjects.SpellData;
import com.vandyke.demoproject.SpellbookAPI.models.Spell;
import com.vandyke.demoproject.SpellbookAPI.models.User;
import com.vandyke.demoproject.SpellbookAPI.repositories.SpellRepository;
import com.vandyke.demoproject.SpellbookAPI.repositories.UserRepository;

import org.testng.annotations.BeforeClass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

@Test
public class SpellServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    SpellRepository spellRepo;

    @Mock
    UserRepository userRepo;

    @InjectMocks
    SpellService spellService;

    @BeforeClass
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterClass
    public void afterClass() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }


    @Test
    public void givenValidData_whenAddSpellButtonClicked_thenSpellShouldBeSaved() throws UserNotFoundException {
        SpellData data = createDummySpellData(1l, 1l);
        Spell spell = createDummySpell(1l, 1l);
        Optional<User> user = createDummyUser(1l);

        when(spellRepo.save(any())).thenReturn(spell);
        when(userRepo.findById(any())).thenReturn(user);

        SpellData returnedSpellData = spellService.createOrEditSpell(data);
        Spell returnedSpell = spellService.dataToSpell(returnedSpellData);
        assertEquals(returnedSpell, spell);

        verify(spellRepo).save(spell);
    }

    @Test(expectedExceptions = {UserNotFoundException.class})
    public void givenInvalidUserId_whenAddSpellButtonClicked_thenExceptionShouldBeThrown() throws UserNotFoundException {
        SpellData data = createDummySpellData(1l, 1l);
        Spell spell = createDummySpell(1l, 1l);
        Optional<User> user = Optional.empty();

        when(spellRepo.save(any())).thenReturn(spell);
        when(userRepo.findById(any())).thenReturn(user);

        spellService.createOrEditSpell(data);
    }

    @Test
    public void givenValidSpellId_whenDeleteButtonClicked_thenSpellShouldBeDeleted() throws SpellNotFoundException {
        doNothing().when(spellRepo).deleteById(anyLong());
        when(spellRepo.existsById(anyLong())).thenReturn(true);

		spellService.deleteSpell(1l);
        verify(spellRepo).deleteById(anyLong());
    }

    @Test(expectedExceptions = {SpellNotFoundException.class})
    public void givenInvalidSpellId_whenDeleteRequestRecieved_thenExceptionShouldBeThrown() throws SpellNotFoundException {
        when(spellRepo.existsById(anyLong())).thenReturn(false);

        spellService.deleteSpell(1l);
    }

    @Test
    public void givenValidUserId_whenFrontEndRequestsUsersSpells_thenListOfSpellsShouldBeReturned() throws UserNotFoundException {
        List<SpellData> dataList = new ArrayList<SpellData>();
        List<Spell> spellList = new ArrayList<Spell>();

        dataList.add(createDummySpellData(1l, 1l));
        dataList.add(createDummySpellData(2l, 1l));

        spellList.add(createDummySpell(1l, 1l));
        spellList.add(createDummySpell(2l, 1l));

        when(spellRepo.findAllByUser(any())).thenReturn(spellList);
        when(userRepo.findById(1l)).thenReturn(createDummyUser(1l));

        List<SpellData> returnedData = spellService.getUserSpells(1l);

        Assert.assertEquals(returnedData, dataList);
    }

    @Test(expectedExceptions = {UserNotFoundException.class})
    public void givenInvalidUserId_whenFrontEndRequestsUsersSpells_thenExceptionShouldBeThrown() throws UserNotFoundException {
        when(userRepo.findById(anyLong())).thenReturn(Optional.empty());

        spellService.getUserSpells(1l);
    }

    private SpellData createDummySpellData(Long spellId, Long userId) {
        SpellData data = new SpellData();

        data.setName("TestSpell");
        data.setId(spellId);
        data.setUserId(userId);
        data.setLevel(2);
        data.setRange("100ft");
        data.setCastingTime("1 Action");
        data.setComponents("V,S");
        data.setDuration("Instantaneous");
        data.setDamageType("Fire");
        data.setDamageAmount("2d8");
        data.setDescription("This is a test spell.");

        return data;
    }

    private Spell createDummySpell(Long spellId, Long userId) {
        Spell data = new Spell();

        data.setName("TestSpell");
        data.setId(spellId);
        data.setUser(createDummyUser(userId).get());
        data.setLevel(2);
        data.setRange("100ft");
        data.setCastingTime("1 Action");
        data.setComponents("V,S");
        data.setDuration("Instantaneous");
        data.setDamageType("Fire");
        data.setDamageAmount("2d8");
        data.setDescription("This is a test spell.");

        return data;
    }

    private Optional<User> createDummyUser(Long userId) {
        User user = new User();

        user.setId(userId);
        user.setUsername("test");
        user.setPassword("pass");

        Optional<User> op = Optional.of(user);

        return op;
    }
}
