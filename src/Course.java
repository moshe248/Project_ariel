
public class Course {
	final int DAYS = 6;
	final int HOURS = 13;
	//int ID;
	final int COURSE_LENGTH;
	Teacher[] C_teachers;
	int course_index;// index of this course in the general course list in the model
	//final int model
	String course_name="";;
	
	boolean _is_set; ///is scheduled
	int Day;
	int Hour;	
	int T_index_in_C;
	Teacher teacher;
	

	public Course(String name, int course_length, Teacher[] teachers){
		//ID = course_num;
		course_name = name;
		COURSE_LENGTH = course_length;
		this.C_teachers = teachers;
		teacher=null;
		first_init();
	}
	public void set_course_index(int i){
		this.course_index=i;
	}
	public void set_time(int day, int hour, int teacher_index_in_C){
		this.Day = day;
		this.Hour = hour;
		this.T_index_in_C = teacher_index_in_C;
		this.teacher = C_teachers [ teacher_index_in_C];
		_is_set = true;
		this.teacher.set_time(day, hour, COURSE_LENGTH);
	}

	
	public boolean is_teacher_available(int day, int hour, int C_techer_inddex){
		 return   C_teachers[C_techer_inddex]. T_scheduale.is_available(day, hour,COURSE_LENGTH);
	}
	public void print(){
		//System.out.println("Course ID: "+ ID);
		/*System.out.println("Teacher index: "+ teacher);
		System.out.println("Day-"+Day);
		System.out.println("Hour-"+Hour);*/
	}
	public void first_init(){
		_is_set = false;
		Day = -1;
		Hour = -1;
		T_index_in_C = -1;
		teacher=null;
		
	}
	public boolean isFlag() {
		return _is_set;
	}
	
	//הפונ מחזירה איזה אינדקס של הקורס ניתן לשנה 
	public int find_next_indexes(){
		if(T_index_in_C == -1 ||Hour == -1 || Day == -1  )System.err.println("first init eror func in course");
		if( T_index_in_C+1 < C_teachers.length)return 3;
		else if(Hour+1< HOURS)return 2;
		else if(Day+1<DAYS)return 1;
		else return -1;
	}
	
	public int getCOURSE_LENGTH() {
		return COURSE_LENGTH;
	}
	
	public void clear_teacher_course(){
		teacher.clear_course_from_teacher_schedule(this);
	}
	public void clear_course_and_teacher(){
		clear_teacher_course();
		first_init();
	}
	public String course_to_String(){
		return "D"+Day+"H" + Hour +"T"+ T_index_in_C+"|"; 
	}
	public long COURSE_option_to_run(){
		
		long ans;
		ans = 5*(14-COURSE_LENGTH);
		if(COURSE_LENGTH < 8) ans = ans + 8-COURSE_LENGTH;
		ans = ans * C_teachers.length;
		if(ans < 0) return -1;
		else return ans;
	}

}
