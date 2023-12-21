import java.time.*;

// Person Class
class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String contact;
    private int age;
    private LocalDate birthDate;
    

    // Constructor
    public Person(String firstName, String lastName, String middleName, String email, String contact, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.middleName = middleName;
        this.email = email;
        this.contact = contact;
        
        LocalDateTime currentDate = LocalDateTime.now();
        Period agePeriod = Period.between(birthDate, currentDate.toLocalDate());
        this.age = agePeriod.getYears();
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Also sets the age with the corresponding birthDate
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        LocalDateTime currentDate = LocalDateTime.now();
        Period agePeriod = Period.between(birthDate, currentDate.toLocalDate());
        this.age = agePeriod.getYears();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getAge() {
        return age;
    }

}