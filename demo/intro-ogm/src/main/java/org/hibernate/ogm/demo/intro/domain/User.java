package org.hibernate.ogm.demo.intro.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * @author Emmanuel Bernard
 */
@Entity @Indexed
public class User {

	public User() {}

	public User(String firstName, String lastName, Date birthDate, Gender gender, int credits, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.credits = credits;
		this.login = new Login(username, password);
	}

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	public Long getId() { return id; }
	public void setId(Long id) {  this.id = id; }
	private Long id;

	@Field
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) {  this.firstName = firstName; }
	private String firstName;

	@Field
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) {  this.lastName = lastName; }
	private String lastName;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "owner")
	public Set<Address> getAddresses() { return addresses; }
	public void setAddresses(Set<Address> addresses) {  this.addresses = addresses; }
	private Set<Address> addresses = new HashSet<Address>();

	@Temporal(TemporalType.DATE)
	public Date getBirthDate() { return birthDate; }
	public void setBirthDate(Date birthDate) {  this.birthDate = birthDate; }
	private Date birthDate;

	@Enumerated(EnumType.STRING)
	public Gender getGender() { return gender; }
	public void setGender(Gender gender) {  this.gender = gender; }
	private Gender gender;

	public int getCredits() { return credits; }
	public void setCredits(int credits) {  this.credits = credits; }
	private int credits;

	public Login getLogin() { return login; }
	public void setLogin(Login login) {  this.login = login; }
	private Login login;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append( "User" );
		sb.append( "{id=" ).append( id );
		sb.append( ", firstName='" ).append( firstName ).append( '\'' );
		sb.append( ", lastName='" ).append( lastName ).append( '\'' );
		sb.append( ", addresses=" ).append( addresses );
		sb.append( ", birthDate=" ).append( birthDate );
		sb.append( ", gender=" ).append( gender );
		sb.append( ", credits=" ).append( credits );
		sb.append( ", login=" ).append( login );
		sb.append( '}' );
		return sb.toString();
	}
}
