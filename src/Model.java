//הערה כללית לשנות את פונ העזר לפריייווט 
public class Model {
	final int DAYS = 6;
	final int HOURS = 13;
	final String[] week= {"Sunday", "Monday","Tuesday","Wednesday","Thursday","Friday"};

	Course[] courses;
	Schedule M_scheduale;	
	//Teacher[] M_teachers;
	boolean Is_schedule = false;

	Course temp_course;// this variables are for saving memory...
	Teacher temp_teacher;

	/*constructor*/
	public  Model(Course[] courses) {
		this.courses = courses;
		M_scheduale = new Schedule();

		for (int i = 0; i < courses.length; i++) 
		{
			this.courses[i].set_course_index(i);;	
		}
	}
	/**/
	public int model_quality(){
		//how many days
		//how many hols
		//teacher teken looz and stuf



		//הפונקציה תבדוק את טיב המערכת מבחינת שעות עבור סטודנט אחד ותחזיר מספר
		// עבור טווח מספרים מסויים המודל אוטומטית יימחק (למשל שיבוץ של מערכת על פני 6 ימים בשבוע)
		return 0;
	}
	/*assuming valid input 
	 * schedule to the fours time & teacher
	 * update the teacher schedule and the model schedule */


	public void schedual_course(int c, int d, int h, int T_index_in_C){

		//courses[ c ].C_teachers[ T_index_in_C ].set_time(d, h, courses[ c ].COURSE_LENGTH);
		courses[ c ].set_time(d, h,T_index_in_C);
		M_scheduale.set_time(d, h, courses[ c ].COURSE_LENGTH );

	}
	/*public void schedual_course(Course course, int teacher_index, int day, int hour){

		M_teachers[teacher_index].set_time(day, hour, course.COURSE_LENGTH);
		course.set_time(day, hour, teacher_index);
		M_scheduale.set_time(day, hour, course.COURSE_LENGTH);
		//for (int i = 0; i < course.COURSE_LENGTH; i++) {
			//M_scheduale.scheduale[day][hour+i] = true;
		//}
	}*/
	/*assuming valid input
	 *  
	//פה אנחנו מעדיפים לא לשלוח קורס אלא כל מאפיין בפני עצמו בגלל שבריצה בתוך הלולאות המידע הזה נגיש יותר 
	//וזה ישפר את יעילות התוכנית
	 * */
	public boolean is_Model_schedule_available(int day, int hour, int course_length){
		//if(M_scheduale.is_available(day, hour, course_length) == true)return false;
		return M_scheduale.is_available(day, hour, course_length);
		//else return true;
	}
	/* check if all courses of the model has been schedule
	//בדיקה עבור מודל אם כל הקורסים שובצו*/
	public boolean is_all_courses_in(){
		for (int i = 0; i < courses.length; i++) {
			if(courses[i]._is_set == false )return false;
		}
		return true;
	}


	public boolean build_empty_model(){
		return build_next_model(new int[]{0,0,0,0});

	}
	public void clear_course_from_model(Course c ) {
		if(c._is_set ){
			//teachers[ course.teachers[course.teacher ]  ].clear_cours_from_teacher_schedule(course);
			M_scheduale.clear_time(c);
			//c.clear_teacher_course();
			//c.first_init();
			c.clear_course_and_teacher();

		}
	}

	//הפונ מחפשת מהסוף להתחלה לאיזה קורס אפשר לקדם את השיבוץ,  
	//במידה ולא ניתן לקדם הפונ תפנה את הקורס מהלוז של המרצה ותאפס את השעה יום ומרצה שהקורס רשום אליהם
	//במידה ונמצא שיבוץ שאפשר לקדם () לא בהכרח להצליח לשבץ הפונ תחזיר את האינדס של 
	//במידה ולא ימצא שיבוץ לכל המודל בכלל הפונ תחזיר -1
	//תוך כדי החיפוש הפונ' מנקה את הלוז והשדות הלא רלוונטים
	public int[] find_next_model_indexes_and_clean_irrelevant_setting(){

		int which_index_to_advance = -1;
		int C_fail_sched;
		int i;
		for ( C_fail_sched = 0; C_fail_sched < courses.length; C_fail_sched++) 
		{
			if( ! courses[ C_fail_sched ]._is_set)  break;
		}

		for (i = C_fail_sched ; i < courses.length; i++) 
		{
			clear_course_from_model( courses[i] );
		}

		for (i = C_fail_sched -1 ; i >= 0; i--) 
		{	
			which_index_to_advance = courses[i].find_next_indexes();
			//if(which_index_to_advance == 0) System.err.println(" which index to advance");
			//אם אי אפשר לקדם אף אינדקס
			//הבדיקה היא ברמת הגבול ללא בדיקות האם פנוי וכו
			if( which_index_to_advance > 0)
			{			
				//	ans = advance_next_course_indexes(which_index_to_advance, courses[i]);
				int ans[] = new int[] {i,courses[i].Day,courses[i].Hour,courses[i].T_index_in_C};
				ans[ which_index_to_advance]++;
				if (which_index_to_advance <3 ) ans[3] = 0;
				if (which_index_to_advance <2 ) ans[2] = 0;
				if (which_index_to_advance <1 ) ans[1] = 0;			

				clear_course_from_model(courses[i]);
				return ans;
			}				
			else clear_course_from_model(courses[i]);
		}
		return new int [] {-1,-1,-1,-1}; 

	}
	//פונ' עזר עבור 
	//build_next_model()
	/*	פונ' לא חכמה
	 * 	הפונ מנסה לבנות את המודל החל מאינדקס מסוים
	 *  אם היא נתקעלת במהלך החיפוש היא לא מתקנת את עצמה או מחפשת פרון אחר אלא שולחת 
	 *  false so build 
	 */
	public boolean build_next_model(int[] next_model_indexes){
		//למה במיקום 1 במערך ולא 0?
		if(next_model_indexes[0] == -1 )return false;
		boolean is_course_schedule;
		for (int i = next_model_indexes[0]; i < courses.length; i++)
		{	
			is_course_schedule = false; // same as courses[i].is_set		
			for (int d = next_model_indexes[1]; d < DAYS && is_course_schedule == false; d++)
			{	
				for (int h = next_model_indexes[2]; h < HOURS &&is_course_schedule==false; h++)
				{ 	// בדיקה האם פנוי במערכת הכללית של המודל
					if ( is_Model_schedule_available(d, h, courses[i].COURSE_LENGTH) && is_course_schedule==false)
					{
						for (int t = next_model_indexes[3]; t < courses[i].C_teachers.length && is_course_schedule==false ; t++)
						{
							// בדיקה האם פנוי במערכת של מרצה
							if( courses[i].is_teacher_available(d, h, t))
								//if (  courses[i].C_teachers[t].is_teacher_available(d, h, courses[i].getCOURSE_LENGTH()))
							{
								//schedual_course(courses[i], courses[i].C_teachers[t], d, h);
								schedual_course( i, d, h,t);
								//								schedual_course(courses[i], courses[i].C_teachers[t], d, h);
								is_course_schedule = true;

							}
							else ;//go back
						}
						next_model_indexes[3] = 0;

					}
				}
				next_model_indexes[2] = 0;

			}

			//אם סיימתי עם הקורס בהכרח או ששיבצתי בהצלחה או שלא קיים לקורס שיבןץ אפשרי
			if(courses[i]._is_set == false) return false;
			next_model_indexes[1] = 0;
		}		
		return true;
	}
	//מהסה לבנות בלולאה עד שמצליח או שנגמרו האפשרויות
	public boolean build_next_model(){
		Is_schedule = false;

		//int [] indexes = new int[4];
		int [] indexes = new int[]{0,0,0,0};
		//indexes = find_next_model_index();
		boolean try_to_build;// = false;
		//כל זמן שנכשלנו בבניית המודל אבל עדיין יש אפשרויות נוספות לבדוק
		while( indexes[0] != -1 /* && try_to_build == false*/ )
		{
			indexes = find_next_model_indexes_and_clean_irrelevant_setting();
			try_to_build = build_next_model(indexes);
			if ( try_to_build == true )
			{
				Is_schedule = true ;
				return true;
			}
			//else indexes = find_next_model_index(); 
		}	
		return false;			
	}
	/*
	 * פה בכוונה אני משתמש בשתי פונ' שונות ולא בשניהם בנקס מודל כי אחרת אני מפספס את מקרה האפס או מגיעה לשגיאת קוד
	 * */
	public boolean  schedule_Model()
	{
		boolean succeed;
		if( courses[0]._is_set) succeed = build_next_model();
		else succeed = build_empty_model(); 
		return succeed;

		/*boolean succeed =build_model(); 
		if(succeed) return true;
		else
		{
			succeed = build_next_model();
		}
		return succeed;*/
	}
	public void clear_model(){
		for (int i = 0; i < courses.length; i++) 
		{
			clear_course_from_model(courses[i]);
		}
		Is_schedule=false;
	}
	public void print_course(Course course){
		int hour =course.Hour+9;
		System.out.print("	"+ course.course_name+" "+ week[course.Day]+" "+hour+":00 - "+(hour + course.COURSE_LENGTH)+":00 - "  );
	}
	public void print_model() {

		System.out.println("Model Schedule:");
		M_scheduale.print();
		System.out.println("courses:");
		for (int i = 0; i < courses.length; i++) {
			print_course( courses[i]);
			System.out.print( courses[i].teacher.Teacher_name);
			System.out.println();
		}

	}
	public String model_to_String(){
		String ans="";
		for (int i = 0; i < courses.length; i++) {
			ans = ans+"C"+i + courses[i].course_to_String(); 
		}


		return ans;
	}
	public long MODEL_option_to_runa(){
		long ans = 1;
		for (int i = 0; i < courses.length; i++) {
			ans = ans * courses[i].COURSE_option_to_run();
		}
		return ans;
	}		
}