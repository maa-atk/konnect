package demo.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



// display all meetings
@WebServlet("/allavailablemeetingrooms")
public class ListMeetingRoomsServelet extends HttpServlet{
	public void service (HttpServletRequest request,HttpServletResponse response ) throws IOException {

	//returns an arraylist of type meeting room to the jsp
	    //change
		MeetingService mserv= new MeetingServiceImpl();
		// change type
		ArrayList<Meeting> meetingRooms = mserv.displayFreeMeetingRooms();
		PrintWriter out= response.getWriter();
		// convert array to json using gson
		//https://jar-download.com/artifacts/com.google.code.gson/gson/2.8.2/source-code version
		 String json = new Gson().toJson(meetingRooms);
		//send json
		out.println(meetingRoomJson);
	    
}
}