
public class Teacher {
	final int DAYS = 6;
	final int HOURS = 14;//לדעתי צריך להיות 13 ועבור יום שישי תהיה פונ שתזיז בשעה
	int ID;
	Schedule T_scheduale;
	final int TEKEN;
	//int[] T_courses;
	int actual_time;
	int teken_exception;
	
	boolean is_available;
	
	public Teacher(int iD, int teken, /*int[] t_courses,*/ int exception, int forbididen_hours[][]) {
		ID = iD;
		actual_time = 0;
		T_scheduale = new Schedule();
		this.TEKEN = teken;
		//T_courses = t_courses;
		this.teken_exception = exception;
		init_schedual(forbididen_hours);
	}
	
	public void init_schedual(int forbididen_hours[][]){//תלוי מאוד בדרך קריאת המשתנים מSQL
		int d,h;
		for (int i = 0; i < forbididen_hours.length; i++) {
			for (int j = 0; j < forbididen_hours[0].length; j++) {
				 d =forbididen_hours[i][0] -1;
				 h =forbididen_hours[i][1]  -9;
				
				this.T_scheduale.schedule[  d][h ] = true;
			}
		}
	}

	public boolean is_teacher_available(int day, int hour, int course_length){
		 return T_scheduale.is_available(day, hour, course_length);
	}
	
	
	public int teken_status(int course_length){
		//Returns 0 if actual_time + course_length  < teken = available,
		//1 if actual_time + course_length > teken && < teken_exception, 
		//-1 if actual_time + course_length > teken_exception = not available
		if (actual_time + course_length <= TEKEN) 
			return 1;
		else if (actual_time + course_length > TEKEN + teken_exception)
			return -1;
		else
			return 0;
	}
	public void set_time(int day, int hour, int course_length){
		T_scheduale.set_time(day, hour, course_length);
	}
	public void clear_cours_from_schedule(int day, int hour, int course_length){
		T_scheduale.clear_time(day, hour, course_length);
	}
	public void clear_cours_from_schedule(Course course){
		T_scheduale.clear_time(course);
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
