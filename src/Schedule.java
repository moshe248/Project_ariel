
public class Schedule {
	final int DAYS = 6;
	final int HOURS = 13;
	boolean schedule[][];
	int Forbididen_hours[][] = null;
	
	int max_days_allwed = 2;
	int current_num_of_days = 0;
	int [] num_course_a_day= new int[6];
	
	public Schedule() {
		schedule = new boolean[DAYS][HOURS];
		init_friday();
		init_forbbiden_days();
	}
	public void init_forbbiden_days(){
		for (int i = 0; i < 6; i++) {
			num_course_a_day[i] = 0;
		}
		current_num_of_days = 0;
	}
	public boolean is_day_available(int day){	
		if (max_days_allwed == current_num_of_days )
		{
			if (num_course_a_day[day] == 0 )return false;
			else return true;			
		}		
		else return true;
	}
public void set_days(int day){
		if (num_course_a_day[day] == 0)
		{
			current_num_of_days ++;			
		}		
		num_course_a_day[day]++;	
	}
public void clear_forrbiden_days(int day){
	if( num_course_a_day[day] ==0) return;
	num_course_a_day[day]--;
	if (num_course_a_day[day] == 0)
	{
		current_num_of_days --;			
	}		
}

public void fullSched(){ //initialize Friday. Study end at 15:00 
		for (int i = 0; i < DAYS; i++) {
			for (int j = 0; j < HOURS; j++) { 
				schedule[i][j] = true;
			}
		}
	}
	public void init_friday(){ //initialize Friday. Study end at 15:00 
		for (int i = 7; i < HOURS; i++) { 
			schedule[5][i] = true;
		}
	}
	public void init_forbidden(int forbididen_hours[][]){//תלוי מאוד בדרך קריאת המשתנים מSQL
		this.Forbididen_hours = forbididen_hours;
		int d,h;
		for (int i = 0; i < forbididen_hours.length; i++) {
			for (int j = 0; j < forbididen_hours[0].length; j++) {
				 d =forbididen_hours[i][0] -1;
				 h =forbididen_hours[i][1]  -9;
				
				schedule[ d ][ h ] = true;
			}
		}
	}
	public boolean is_available(int day, int hour, int course_length){
		if ( is_day_available(day ) == false) return false;
		
		if ( day<5 &&  hour + course_length > HOURS ) return false;
		else if( day == 5 && hour + course_length > 7  )return false;

		for (int i = 0; i < course_length; i++) 
		{
			if (schedule[day][hour + i] == true)
				return false;
		}
		return true;
	}
	public void set_time(int day, int hour, int course_length){
		for (int i = 0; i < course_length; i++) {
			schedule[day][hour+i] = true;
		}
		set_days(day);
	}
	public void clear_time(int day, int hour, int course_length){
		for (int i = 0; i < course_length; i++) {
			schedule[day][hour+i] = false;
		}
		clear_forrbiden_days(day);
	}
	public void clear_time( Course course){
		for (int i = 0; i < course.COURSE_LENGTH; i++) {
			schedule[course.Day][course.Hour+i] = false;
		}
		clear_forrbiden_days(course.Day);
	}

	public void clear_scheduale(){
		for (int i = 0; i < DAYS; i++) {
			for (int j = 0; j < HOURS; j++) {
				schedule[i][j] = false;
			}
		}
		init_friday();
		init_forbbiden_days();
		if(Forbididen_hours != null ) init_forbidden(Forbididen_hours);
	}
	public void print() {
		System.out.println("      Sun*****Mun*****Tue*****Wed*****Thu*****Fri");
		int hour;
		String empty="   ";
		String gap="     ";
		String full ="ful";

		for (int i = 0; i < HOURS; i++) {
			hour=i+9;
			System.out.print(hour+":00 ");

			for (int j = 0; j < DAYS; j++) 
			{

				if(	schedule[j][i] == false) System.out.print(empty);
				else System.out.print(full);

				System.out.print(gap);
			}
			System.out.println();
		}

	}

}

