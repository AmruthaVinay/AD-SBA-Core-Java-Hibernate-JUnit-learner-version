package sba.sms.models;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "student")
@NamedQueries({
	@NamedQuery(name = "getAll", query = "from Student"),
	@NamedQuery(name = "getById", query = "from Student where email = :email")
})
public class Student {

	//fields
	@Id
	@Column(columnDefinition = "varchar(50)", name = "email")
	String email;
	@Column(columnDefinition = "varchar(50)", name="name")
	@NonNull
	String name;
	@Column(columnDefinition = "varchar(50)", name="password")
	@NonNull
	String password;
	@ToString.Exclude
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},
	fetch = FetchType.EAGER)
	@JoinTable(name="student_courses",
	joinColumns = @JoinColumn(name="student_email"),
	inverseJoinColumns = @JoinColumn(name="courses_id"))
	Set<Course> courses;
	
	//All args Constructor

	public Student(String email, @NonNull String name, @NonNull String password, Set<Course> courses) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.courses = courses;
	}
	
	//Requried Agrs Constructor
	public Student(String email, @NonNull String name, @NonNull String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}


	//hash code and equals

	@Override
	public int hashCode() {
		return Objects.hash(courses, email, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(courses, other.courses) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}
	

}
