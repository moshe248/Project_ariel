
public class Course {
	final int DAYS = 6;
	final int HOURS = 13;
	//int ID;
	final int COURSE_LENGTH;
	int[] teachers;
	int course_index;
	//final int model
	String course_name="";;
	
	boolean Flag; ///is scheduled
	int Day;
	int Hour;
	int teacher;

	public Course(/*int course_num,*/	String name, int course_length, int[] teachers){
		//ID = course_num;
		course_name = name;
		COURSE_LENGTH = course_length;
		this.teachers = teachers;
		first_init();
	}
	public void set_course_index(int i){
		this.course_index=i;
	}
	public void set_time(int day, int hour, int teacher){
		this.Day = day;
		this.Hour = hour;
		this.teacher = teacher;
		Flag = true;
	}
	public void print(){
		//System.out.println("Course ID: "+ ID);
		/*System.out.println("Teacher index: "+ teacher);
		System.out.println("Day-"+Day);
		System.out.println("Hour-"+Hour);*/
	}
	public void first_init(){
		Flag = false;
		Day = -1;
		Hour = -1;
		teacher = -1;
		
	}
	public boolean isFlag() {
		return Flag;
	}
	
	//הפונ מחזירה איזה אינדקס של הקורס ניתן לשנה 
	public int find_next_indexes(){
		if(teacher+1 < teachers.length)return 2;
		else if(Hour+1< HOURS)return 1;
		else if(Day+1<DAYS)return 0;
		else return -1;
	}
	
	public int getCOURSE_LENGTH() {
		return COURSE_LENGTH;
	}
	





}
