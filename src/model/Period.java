package model;

public class Period implements java.io.Serializable {
	
	private static final long serialVersionUID = 4874433063042918976L;
	private String periodDay;
	private int periodNum;
	private String periodID;
	private String periodTime;
	
	public Period(String periodDay, int periodNum) {
		this.periodDay = periodDay;
		this.periodNum = periodNum;
		this.periodID = periodDay + " " + periodNum;
		this.periodTime = generateTime(periodNum);
	}
	
	public Period(String periodID) {
		this.periodID = periodID;
		String[] pid = periodID.split(" "); 
		this.periodDay = pid[0];
		this.periodNum = Integer.parseInt(pid[1]);
		this.periodTime = generateTime(periodNum);
		
	}
	
	public String getDay() {
		return periodDay;
	}
	
	public void setDay(String pday) {
		periodDay = pday;
	}
	
	public int getPeriod() {
		return periodNum;
	}
	
	public void setPeriod(int pnum) {
		periodNum = pnum;
	}
	
	public String getPID() {
		return periodID;
	}
	
	public void setPID(String pid) {
		periodID = pid;
	}
	
	public void refreshPID() {
		periodID = periodDay + " " + periodNum;
	}
	
	public String getTime() {
		return periodTime;
	}
	
	public void setTime(String ptime) {
		periodTime = ptime;
	}
	
	public String generateTime(int pnum) {
		switch(pnum) {
		case 1:
			return "8:40am - 10:00am";
		case 2:
			return "10:20am - 11:40am";
		case 3:
			return "12:00pm - 1:20pm";
		case 4:
			return "1:40pm - 3:00pm";
		case 5:
			return "3:20pm - 4:40pm";
		case 6:
			return "5:00pm - 6:20pm";
		case 7:
			return "6:40pm - 8:00pm";
		case 8:
			return "8:10pm - 9:30pm";
		case 9:
			return "9:40pm - 11:00pm";
		default:
			return "Invalid time";
		}
	}
	
	// Overriding equals
	public boolean equals(Object o) {
		  if (!(o instanceof Period)) {
		    return false;
		  }
		  Period other = (Period) o;
		  return (periodID.equals(other.periodID));
	}

	public int hashCode() {
		  return periodID.hashCode();
	}
}
