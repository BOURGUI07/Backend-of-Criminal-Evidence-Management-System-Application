/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package main;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import main.dto.request.creation.PersonCreationRequest;
import main.dto.request.update.PersonUpdateRequest;
import main.dto.response.PersonResponse;
import main.entity.Person;
import main.exception.AlreadyExistsException;
import main.exception.EntityNotFoundException;
import main.repo.PersonRepo;
import main.service.PersonService;
import main.util.mapper.PersonMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author hp
 */
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonMapper mapper;
    @Mock
    private PersonRepo repo;
    @InjectMocks
    private PersonService service;
    private Validator validator;
    private Person person = new Person(1,"username","firstname","lastname","CALIFORNIA88@dribble",LocalDate.of(2020, 04, 15));
    private PersonCreationRequest request = new PersonCreationRequest("username","firstname","lastname","CALIFORNIA88@dribble",LocalDate.of(2020, 04, 15));
    private PersonUpdateRequest update = new PersonUpdateRequest("username","firstname","lastname");
    private PersonResponse response = new PersonResponse(1,"username","firstname","lastname",LocalDate.of(2020, 04, 15),person.getCreatedDate());
    public PersonServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        service.setValidator(validator);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    
    @Test
    void testCreate(){
        when(mapper.toEntity(request)).thenReturn(person);
        when(repo.save(person)).thenReturn(person);
        when(mapper.toDTO(person)).thenReturn(response);
        
        assertEquals(response,service.create(request));
        
        verify(repo,times(1)).save(person);
    }
    
    @Test
    void testUpdate(){
        when(repo.findById(1)).thenReturn(Optional.ofNullable(person));
        when(repo.save(person)).thenReturn(person);
        when(mapper.toDTO(person)).thenReturn(response);
        assertEquals(response, service.update(1, update));
        verify(repo,times(1)).save(person);
    }
    
    @Test
    void testFindById(){
        when(repo.findById(1)).thenReturn(Optional.ofNullable(person));
        when(mapper.toDTO(person)).thenReturn(response);
        assertEquals(response,service.findById(1));
    }
    
    @Test
    void findAll(){
        int page = 0;
        int size = 10;
        var pageable = PageRequest.of(page,size);
        var taxPage = new PageImpl<>(List.of(person));
        when(repo.findAll(pageable)).thenReturn(taxPage);
        when(mapper.toDTO(person)).thenReturn(response);
        assertEquals(response,service.findAll(page, size).getContent().get(0));
    }
    
     @Test
    void testDelete(){
        doNothing().when(repo).delete(person);
        when(repo.findById(1)).thenReturn(Optional.of(person));
        service.delete(1);
        verify(repo,times(1)).delete(person);
    }
    
    @Test
    void testNonValidFindById(){
        assertThrows(IllegalArgumentException.class,()-> {
            service.findById(null);
        });
        assertThrows(IllegalArgumentException.class,()-> {
            service.findById(0);
        });
        when(repo.findById(2)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()-> {
            service.findById(2);
        });
    }
    
    @Test
    void testAlreadyExists(){
        PersonCreationRequest request1 = new PersonCreationRequest("username","firstname","lastname","CALIFORNIA88@dribble",LocalDate.of(2020, 04, 15));
        when(repo.existsByUsernameIgnoreCase(request1.username())).thenReturn(Boolean.TRUE);
        assertThrows(AlreadyExistsException.class,()-> {
            service.create(request1);
        });
    }
}
