package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExtendedDataBaseTest{
   private static final  Person PERSON1 = mock(Person.class);
    private static final Person PERSON2 = mock(Person.class);
    private static final Person PERSON3 = mock(Person.class);

    private static final Person[] EXPECTED_PEOPLE ={PERSON1,PERSON2,PERSON3};
    private static final Integer EXPECTED_SIZE = EXPECTED_PEOPLE.length;
    private static final Person EXPECTED_LAST_ELEMENT = EXPECTED_PEOPLE[1];
    private Database database;
    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(EXPECTED_PEOPLE);
    }

    @Test
    public void test_Constructor_Should_Create_CorrectObj(){
        Person[] actualPeople = database.getElements();
        Integer actualSize = actualPeople.length;
        assertArrayEquals("Arrays are not the same!",EXPECTED_PEOPLE,actualPeople);
        assertEquals("Elements count is incorrect!",EXPECTED_SIZE,actualSize);
        assertEquals("Index is incorrect",EXPECTED_SIZE-1,actualSize-1);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_WhenElementsCount_Greater_Than16() throws OperationNotSupportedException {
        Person[] people = new Person[17];
        database=new Database(people);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_When_ElementsAreZero() throws OperationNotSupportedException{
        database = new Database( );
    }
    @Test
    public void test_Add_ShouldAdd_Element_At_The_End_And_Increase_Size() throws OperationNotSupportedException {
        Person person = mock(Person.class);
        database.add(person);
        int actualSize = database.getElements().length;
        Person[] elements = database.getElements();
        assertEquals(person,elements[actualSize-1]);
        assertEquals("Invalid size",EXPECTED_SIZE+1,actualSize);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_Add_Null_ShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_ShouldRemove_Successfully_AndDecrease_Size() throws OperationNotSupportedException {
        database.remove();
        Person[] elements = database.getElements();
        Person actualLastEl = elements[elements.length-1];
        int actualSize = database.getElements().length;
        assertEquals("Invalid size",EXPECTED_SIZE-1,actualSize);
        assertEquals(EXPECTED_LAST_ELEMENT,actualLastEl);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_Remove_ShouldThrow_When_Database_Empty() throws OperationNotSupportedException{
        for (int i = 0; i <=EXPECTED_SIZE; i++) {
            database.remove();
        }
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByUsername_ShouldThrow_When_UserNotPresent() throws OperationNotSupportedException {
        database.findByUsername("Kaloyan");
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByUsername_ShouldThrow_When_UserParamIsNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }
    @Test
    public void test_FindByUsername_ShouldReturn_CorrectPerson() throws OperationNotSupportedException {
        when(PERSON1.getUsername()).thenReturn("Kaloyan");
        Person actualPerson = database.findByUsername("Kaloyan");

        assertEquals("Invalid username!",PERSON1.getUsername(),actualPerson.getUsername());
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_FindById_ShouldThrow_When_UserWithIdNotPresent() throws OperationNotSupportedException {
        database.findById(5);
    }
    @Test
    public void test_FindById_ShouldReturn_CorrectPerson() throws OperationNotSupportedException {
        when(PERSON1.getId()).thenReturn(5);
        Person actualPerson = database.findById(5);

        assertEquals("Invalid id!",PERSON1.getId(),actualPerson.getId());
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_FindById_ShouldThrow_When_Id_Duplicated() throws OperationNotSupportedException{
        database.findById(0);
    }
}
