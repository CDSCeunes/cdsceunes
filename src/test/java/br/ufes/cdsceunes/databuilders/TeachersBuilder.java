package br.ufes.cdsceunes.databuilders;

import br.ufes.cdsceunes.model.Teacher;

public class TeachersBuilder {
	
	private static int count = 1;
	
	public static Teacher createTeacher() {
		Teacher t = new Teacher();
		t.setName("Teacher " + count++);
		return t;
	}
	
	public static Teacher createTeacher(String name) {
		Teacher teacher = new Teacher();
		teacher.setName(name);
		return teacher;
	}

}
