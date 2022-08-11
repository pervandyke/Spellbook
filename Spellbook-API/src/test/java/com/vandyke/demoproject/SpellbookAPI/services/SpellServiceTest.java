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
        SpellData data = createDummySpellData();
        Spell spell = createDummySpell();
        Optional<User> user = createDummyUser();

        when(spellRepo.save(any())).thenReturn(spell);
        when(userRepo.findById(any())).thenReturn(user);

        Spell returnedSpell = spellService.createSpell(data);
        assertEquals(returnedSpell, spell);

        verify(spellRepo).save(spell);
    }

    @Test(expectedExceptions = {UserNotFoundException.class})
    public void givenInvalidUserId_whenAddSpellButtonClicked_thenErrorShouldBeThrown() throws UserNotFoundException {
        SpellData data = createDummySpellData();
        Spell spell = createDummySpell();
        Optional<User> user = Optional.empty();

        when(spellRepo.save(any())).thenReturn(spell);
        when(userRepo.findById(any())).thenReturn(user);

        spellService.createSpell(data);
    }

    @Test
    public void givenValidSpellId_whenDeleteButtonClicked_thenSpellShouldBeDeleted() throws SpellNotFoundException {
        doNothing().when(spellRepo).deleteById(anyLong());
        when(spellRepo.existsById(anyLong())).thenReturn(true);

		spellService.deleteSpell(1l);
        verify(spellRepo).deleteById(anyLong());
    }

    @Test(expectedExceptions = {SpellNotFoundException.class})
    public void givenInvalidSpellId_whenDeleteRequestRecieved_thenErrorShouldBeThrown() throws SpellNotFoundException {
        when(spellRepo.existsById(anyLong())).thenReturn(false);
        
        spellService.deleteSpell(1l);
    }

    @Test
    public void editSpellTest() {
        throw new RuntimeException("Test not implemented");
    }

    @Test
    public void getSpellByIdTest() {
        throw new RuntimeException("Test not implemented");
    }

    @Test
    public void getSpellsTest() {
        throw new RuntimeException("Test not implemented");
    }

    @Test
    public void getUserSpellsTest() {
        throw new RuntimeException("Test not implemented");
    }

    @Test
    public void dataToSpellTest() {
        throw new RuntimeException("Test not implemented");
    }

    @Test
    public void spellToDataTest() {
        throw new RuntimeException("Test not implemented");
    }

    private SpellData createDummySpellData() {
        SpellData data = new SpellData();

        data.setName("TestSpell");
        data.setId(1l);
        data.setUserId(1l);
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

    private Spell createDummySpell() {
        Spell data = new Spell();

        data.setName("TestSpell");
        data.setId(1l);
        data.setUser(createDummyUser().get());
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

    private Optional<User> createDummyUser() {
        User user = new User();

        user.setId(1l);
        user.setUsername("test");
        user.setPassword("pass");

        Optional<User> op = Optional.of(user);

        return op;
    }
}
