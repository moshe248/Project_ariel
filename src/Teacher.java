
public class Teacher {
	final int DAYS = 6;
	final int HOURS = 13;//לדעתי צריך להיות 13 ועבור יום שישי תהיה פונ שתזיז בשעה
	//int ID;
	String Teacher_name="";
	Schedule T_scheduale;
	//final int TEKEN;
	//int actual_time;
	//int teken_exception;

	boolean is_available;

	/*public Teacher(String name,int iD, int teken, int exception, int forbididen_hours[][]) {
		Teacher_name = name;
		ID = iD;
		actual_time = 0;
		T_scheduale = new Schedule();
		//this.TEKEN = teken;
		//T_courses = t_courses;
		this.teken_exception = exception;
		init_schedual(forbididen_hours);
	}*/
	public Teacher(String name) {
		Teacher_name = name;
		T_scheduale = new Schedule();
	}

	public void init_T_schedual(int forbididen_hours[][]){//תלוי מאוד בדרך קריאת המשתנים מSQL
		T_scheduale.init_forbidden(forbididen_hours);	
	}

	public boolean is_teacher_available(int day, int hour, int course_length){
		return T_scheduale.is_available(day, hour, course_length);
	}


	/*public int teken_status(int course_length){
		//Returns 0 if actual_time + course_length  < teken = available,
		//1 if actual_time + course_length > teken && < teken_exception, 
		//-1 if actual_time + course_length > teken_exception = not available
		if (actual_time + course_length <= TEKEN) 
			return 1;
		else if (actual_time + course_length > TEKEN + teken_exception)
			return -1;
		else
			return 0;
	}*/
	public void set_time(int day, int hour, int course_length){
		T_scheduale.set_time(day, hour, course_length);
	}
	public void clear_course_from_schedule(int day, int hour, int course_length){
		T_scheduale.clear_time(day, hour, course_length);
	}
	public void clear_course_from_teacher_schedule(Course course){
		T_scheduale.clear_time(course);
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/
	public void print_teacher() {
		System.out.println(Teacher_name+":" );
		T_scheduale.print();

	}

}
