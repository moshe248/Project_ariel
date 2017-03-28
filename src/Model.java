
public class Model {
	Course[] courses;
	Schedule M_scheduale;
	final int DAYS = 6;
	final int HOURS = 14;
	Teacher[] teachers;
	///notr


	public  Model(Course[] courses, Teacher[] teachers) {
		this.courses = courses;
		for (int i = 0; i < courses.length; i++) {
			this.courses[i].set_course_index(i);;	
		}
		this.teachers = teachers;
		M_scheduale = new Schedule();
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
	public int[] advance_next_course_indexes(int field,Course c){
		int day = c.Day;
		int hour = c.Hour;
		int teacher_ = c.teacher;

		switch(field){
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
	//במידה ונצא שיבוץ שאפשר לקדם () לא בהכרח להצליח לשבץ הפונ תחזיר את האינדס של 
	//במידה ולא ימצא שיבוץ לכל המודל בכלל הפונ תחזיר -1
	public int[] find_next_model_index(){
		int which_index_to_advance;
		int ans[];
		for (int i = courses.length-1 ; i >= 0; i--) {
			//צריך להוסיך בדיקה להתחלי לבדוק אינקסים רק מהקורס הראשון שטרו בפלאג
			which_index_to_advance = courses[i].find_next_indexes();
			if( which_index_to_advance != -1)
			{					
				ans = advance_next_course_indexes(which_index_to_advance, courses[i]);
				teachers[ courses[i].teacher ].clear_cours_from_schedule(courses[i]);
				courses[i].first_init();
				return ans;
			}
			else
			{
				teachers[ courses[i].teacher ].clear_cours_from_schedule(courses[i]);
				courses[i].first_init();				
			}

		}

		return new int[] {-1,-1,-1,-1};
	}
	public boolean build_next_model(int[] next_model_indexes){
		boolean first_run = true;
		boolean schedual_flag;
		for (int i = 0; i < courses.length; i++)
		{	if ( first_run ) i =next_model_indexes[0];
		schedual_flag=false;
		if (courses[i].isFlag() == false)///בכלל בדיקה מיותרת 
		{//בדיקה אם הקורס לא משובץ
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
								System.out.println("teacher "+t+" course "+i+" len: "+courses[i].COURSE_LENGTH );
								//teachers[t].T_scheduale.print();
							}
						}
					}
				}
			}
		}
		//אם סיימתי עם הקורס בהכרח או ששיבצתי בהצלחה או שלא קיים לקורס שיבןץ אפשרי
		if(courses[i].Flag == false)return false;
		}
		M_scheduale.print();
		//courses[0].print();
		return true;
	}
	//מהסה לבנות בלולאה עד שמצליח או שנגמרו האפשרויות
	public boolean build_next_model(){
		int [] indexes = new int[4];
		indexes = find_next_model_index();
		boolean try_to_build = false;
		//כל זמן שנכשלנו בבניית המודל אבל עדיין יש אפשרויות נוספות לבדוק
		while( indexes[0] != -1  && try_to_build == false )
		{
			try_to_build= build_next_model(indexes);
			if( try_to_build == true)return true;
			else indexes = find_next_model_index(); 
		}	
		return false;			
	}

	
}