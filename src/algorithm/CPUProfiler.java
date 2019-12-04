package algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CPUProfiler {
	public static void main(String[] args) {
		List<Record> records = new ArrayList<Record>();
		records.add(new Record(0, Event.ENTER, "main"));
		records.add(new Record(0, Event.ENTER, "foo"));
		records.add(new Record(10, Event.EXIT, "foo"));
		records.add(new Record(10, Event.ENTER, "bar"));
		records.add(new Record(15, Event.ENTER, "baz"));
		records.add(new Record(17, Event.EXIT, "baz"));
		records.add(new Record(30, Event.EXIT, "bar"));
		records.add(new Record(30, Event.EXIT, "main"));
		
		printCPUProfileOutput(records);
	}

	
	private static void printCPUProfileOutput(List<Record> records) {
		Map<Record, RecordWrapper> recordToWrapperMap = new LinkedHashMap<Record, RecordWrapper>();
		ArrayDeque<RecordWrapper> stack = new ArrayDeque<RecordWrapper>();
		int depth = 0;
		for(Record record: records) {
			if(record.event == Event.ENTER) {
				RecordWrapper wrapper = new RecordWrapper(record, depth);
				recordToWrapperMap.put(record, wrapper);
				stack.push(wrapper);
				depth++;
			} else {
				RecordWrapper wrapper = stack.pop();
				depth--;
				wrapper.setTimeDiff( record.getTime() - wrapper.getRecord().getTime() );
			}
		}
		StringBuilder sb = new StringBuilder();
		String del = "";
		for(RecordWrapper wrapper: recordToWrapperMap.values()) {
			sb.append(del);
			for(int i=0; i<wrapper.getDepth(); i++) {
				sb.append("-");
			}
			sb.append(wrapper.getRecord().getName());
			sb.append(" ");
			sb.append(wrapper.getTimeDiff());
			del = "\n";
		}
		System.out.println(sb.toString());
	}

	public static class RecordWrapper {
		Record record;
		Long timeDiff;
		Integer depth;
		public RecordWrapper(Record record, Integer depth) {
			this.record = record;
			this.depth = depth;
		}
		
		public Record getRecord() {
			return record;
		}
		
		public Integer getDepth() {
			return depth;
		}
		
		public Long getTimeDiff() {
			return timeDiff;
		}
		
		public void setTimeDiff(Long timeDiff) {
			this.timeDiff = timeDiff;
		}
	}

	public static class Record {
		Long time = null;
		Event event = null;
		String name = null;
		public Record(long time, Event event, String name) {
			this.time = time;
			this.event = event;
			this.name = name;
		}
		
		public Long getTime() {
			return time;
		}
		
		public Event getEvent() {
			return event;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public static enum Event { ENTER, EXIT }
}

// OUTPUT
//main 30
//-foo 10
//-bar 20
//--baz 2
