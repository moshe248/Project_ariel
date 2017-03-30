
public class Model {
	final int DAYS = 6;
	final int HOURS = 13;
	final String[] week= {"Sunday", "Monday","Tuesday","Wednesday","Thursday","Friday"};
	Course[] courses;
	Schedule M_scheduale;	
	Teacher[] teachers;
	
	
	

	public  Model(Course[] courses, Teacher[] teachers) {
		this.courses = courses;
		this.teachers = teachers;
		M_scheduale = new Schedule();
		
		for (int i = 0; i < courses.length; i++) 
		{
			this.courses[i].set_course_index(i);;	
		}
	}

	public int model_quality(){
		//הפונקציה תבדוק את טיב המערכת מבחינת שעות עבור סטודנט אחד ותחזיר מספר
		// עבור טווח מספרים מסויים המודל אוטומטית יימחק (למשל שיבוץ של מערכת על פני 6 ימים בשבוע)
		return 0;
	}
	public void schedual_course(Course course, int teacher_index, int day, int hour){
		teachers[teacher_index].set_time(day, hour, course.COURSE_LENGTH);
		course.set_time(day, hour, teacher_index);
		M_scheduale.set_time(day, hour, course.COURSE_LENGTH);
		/*for (int i = 0; i < course.COURSE_LENGTH; i++) {
			M_scheduale.scheduale[day][hour+i] = true;
		}*/
	}

	//פה אנחנו מעדיפים לא לשלוח קורס אלא כל מאפיין בפני עצמו בגלל שבריצה בתוך הלולאות המידע הזה נגיש יותר 
	//וזה ישפר את יעילות התוכנית
	public boolean is_Model_schedule_available(int day, int hour, int course_length){
		//if(M_scheduale.is_available(day, hour, course_length) == true)return false;
		return M_scheduale.is_available(day, hour, course_length);
		//else return true;
	}

	//בדיקה עבור מודל אם כל הקורסים שובצו
	public boolean is_all_courses_in(){
		for (int i = 0; i < courses.length; i++) {
			if(courses[i].Flag == false )return false;
		}
		return true;
	}
	///הייתי משנה את המימוש כך השפונ' תקרא ל
	///	build_next_model
	///the function may not succeed
	public boolean build_model(){
		boolean schedual_flag;
		for (int i = 0; i < courses.length; i++)
		{	schedual_flag = false;

		for (int d = 0; d < DAYS && schedual_flag == false; d++)
		{
			for (int h = 0; h < HOURS &&schedual_flag == false; h++)
			{
				if ( is_Model_schedule_available(d, h, courses[i].COURSE_LENGTH) &&schedual_flag==false)
				{// בדיקה האם פנוי במערכת הכללית של המודל
					for (int t = 0; t < courses[i].teachers.length &&schedual_flag==false ; t++)
					{
						if (teachers[  courses[i].teachers[t] ].is_teacher_available(d, h, courses[i].getCOURSE_LENGTH()))
						{// בדיקה האם פנוי במערכת של מרצה
							schedual_course(courses[i], courses[i].teachers[t], d, h);
							schedual_flag = true;
							//System.out.println("teacher "+t+" course "+i+" len: "+courses[i].COURSE_LENGTH );
							//teachers[t].T_scheduale.print();
						}
					}
				}
			}
		}

		//אם סיימתי עם הקורס בהכרח או ששיבצתי בהצלחה או שלא קיים לקורס שיבןץ אפשרי
		if(courses[i].Flag == false)return false;


		}
		//פה צריך לשלוח לבניית מודל החל מאינדקס
		M_scheduale.print();
		//courses[0].print();
		if(courses[ courses.length-1].Flag == true) return true;
		else return false;
	}
	public void clear_course_from_model(Course course ) {
		teachers[ course.teacher ].clear_cours_from_teacher_schedule(course);
		M_scheduale.clear_time(course);
		course.first_init();		
	}


	///פונ' עזר עבור 
	//find_next_model_index()
	public int[] advance_next_course_indexes(int which_index_to_advance,Course c){
		int day = c.Day;
		int hour = c.Hour;
		int teacher_ = c.teacher;

		switch(which_index_to_advance){
		case 2:
			teacher_ ++;
			break;
		case 1:
			teacher_= 0;
			hour++;
			break;
		case 0:
			teacher_ = 0;
			hour = 0;
			day++;
			break;			
		}	
		return new int[] {c.course_index,day,hour,teacher_};
	}
	//הפונ מחפשת מהסוף להתחלה לאיזה קורס אפשר לקדם את השיבוץ,  
	//במידה ולא ניתן לקדם הפונ תפנה את הקורס מהלוז של המרצה ותאפס את השעה יום ומרצה שהקורס רשום אליהם
	//במידה ונמצא שיבוץ שאפשר לקדם () לא בהכרח להצליח לשבץ הפונ תחזיר את האינדס של 
	//במידה ולא ימצא שיבוץ לכל המודל בכלל הפונ תחזיר -1
	//תוך כדי החיפוש הפונ' מנקה את הלוז והשדות הלא רלוונטים
	public int[] find_next_model_index_and_clean_irrelevant_setting(){
		int which_index_to_advance;
		int ans[];
		for (int i = courses.length-1 ; i >= 0; i--) {
			if(courses[i].isFlag() )
			{	
				which_index_to_advance = courses[i].find_next_indexes();
				if( which_index_to_advance != -1)
				{					
					ans = advance_next_course_indexes(which_index_to_advance, courses[i]);
					clear_course_from_model(courses[i]);
					//teachers[ courses[i].teacher ].clear_cours_from_teacher_schedule(courses[i]);
					//courses[i].first_init();
					return ans;
				}
				else
				{
					clear_course_from_model(courses[i]);
					//teachers[ courses[i].teacher ].clear_cours_from_teacher_schedule(courses[i]);
					//courses[i].first_init();				
				}
			}
		}
		return new int[] {-1,-1,-1,-1};
	}
	//פונ' עזר עבור 
	//build_next_model()
	public boolean build_next_model(int[] next_model_indexes){
		if(next_model_indexes[0]==-1 )return false;
		boolean first_run = true;
		boolean schedual_flag;
		for (int i = 0; i < courses.length; i++)
		{	if ( first_run ) i =next_model_indexes[0];
		schedual_flag=false;
		//if (courses[i].isFlag() == false)///בכלל בדיקה מיותרת 
		//{//בדיקה אם הקורס לא משובץ
		for (int d = 0; d < DAYS &&schedual_flag==false; d++)
		{	
			if ( first_run ) d =next_model_indexes[1];
			for (int h = 0; h < HOURS &&schedual_flag==false; h++)
			{
				if ( first_run ) h =next_model_indexes[2];
				if ( is_Model_schedule_available(d, h, courses[i].COURSE_LENGTH) && schedual_flag==false)
				{// בדיקה האם פנוי במערכת הכללית של המודל

					for (int t = 0; t < courses[i].teachers.length && schedual_flag==false ; t++)
					{
						if ( first_run ) t =next_model_indexes[3];
						first_run = false;

						if ( teachers[ courses[i].teachers[t] ].is_teacher_available(d, h, courses[i].getCOURSE_LENGTH()))
						{// בדיקה האם פנוי במערכת של מרצה
							schedual_course(courses[i], courses[i].teachers[t], d, h);
							schedual_flag=true;
							//System.out.println("teacher "+t+" course "+i+" len: "+courses[i].COURSE_LENGTH );
							//teachers[t].T_scheduale.print();
						}
					}
				}
			}
		}
		//}
		//אם סיימתי עם הקורס בהכרח או ששיבצתי בהצלחה או שלא קיים לקורס שיבןץ אפשרי
		if(courses[i].Flag == false)return false;
		}
		//M_scheduale.print();
		//courses[0].print();
		return true;
	}
	//מהסה לבנות בלולאה עד שמצליח או שנגמרו האפשרויות
	public boolean build_next_model(){
		//int [] indexes = new int[4];
		int [] indexes = new int[]{0,0,0,0};
		//indexes = find_next_model_index();
		boolean try_to_build;// = false;
		//כל זמן שנכשלנו בבניית המודל אבל עדיין יש אפשרויות נוספות לבדוק
		while( indexes[0] != -1 /* && try_to_build == false*/ )
		{
			indexes = find_next_model_index_and_clean_irrelevant_setting();
			try_to_build= build_next_model(indexes);
			if( try_to_build == true)return true;
			//else indexes = find_next_model_index(); 
		}	
		return false;			
	}


	public boolean  schedule_Model()
	{
		boolean succeed =build_model(); 
		if(succeed) return true;
		else
		{
			succeed = build_next_model();
		}

		return succeed;
	}
	public void print_course(Course course){
		int hour =course.Hour+9;
		System.out.print( course.course_name+" "+ week[course.Day]+" "+hour+":00 "  );
	}
	public void print_model() {
		
		System.out.println("Model Schedule:");
		M_scheduale.print();
		System.out.println("courses:");
		for (int i = 0; i < courses.length; i++) {
			print_course( courses[i]);
			System.out.print( teachers[courses[i].teacher].Teacher_name);
			System.out.println();
		}
		
	}
}