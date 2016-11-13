package br.ufes.cdsceunes.jsonview;

public class View {
	public interface Summary { };
	public interface SummaryWithDetails extends Summary {};
	public interface TeacherWithClasses extends Summary, OfferedClassWithSemester {};
	public interface Preferences extends Summary {};
	public interface OfferedClass extends Summary {};
	public interface OfferedClassWithSemester extends Summary {};
	public interface DepartmentWithTeachers extends Summary {};
	public interface NeverShow { };
}
