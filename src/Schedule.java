
public class Schedule {
	final int DAYS = 6;
	final int HOURS = 13;
	boolean schedule[][];

	public Schedule() {
		schedule = new boolean[DAYS][HOURS];
		init_friday();
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
	public boolean is_available(int day, int hour, int course_length){
		if (hour + course_length > HOURS) return false;
		for (int i = 0; i < course_length; i++) {
			if (schedule[day][hour + i] == true)
				return false;
		}
		return true;
	}
	public void set_time(int day, int hour, int course_length){
		for (int i = 0; i < course_length; i++) {
			schedule[day][hour+i] = true;
		}
	}
	public void clear_time(int day, int hour, int course_length){
		for (int i = 0; i < course_length; i++) {
			schedule[day][hour+i] = false;
		}
	}
	public void clear_time( Course course){
		for (int i = 0; i < course.COURSE_LENGTH; i++) {
			schedule[course.Day][course.Hour+i] = false;
		}
	}

	public void clear_scheduale(){
		for (int i = 0; i < DAYS; i++) {
			for (int j = 0; j < HOURS; j++) {
				schedule[i][j] = false;
			}
		}
		init_friday();
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

