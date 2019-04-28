
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Signal {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		if(args.length != 0){
			//获取最终消息
			List<Message> list = checkData();
			for(String arg : args){
				if(!isInteger(arg) || Integer.valueOf(arg) < 0){
					System.out.println("Error: illegal message ID");
				}else{
					int index = Integer.valueOf(arg);
					if(index > list.size()){
						System.out.println("Error: Cannot find " + arg);
					}else{
						Message msg = list.get(index-1);
						if(msg.isCrash()){
							System.out.println("Error: " + arg);
						}else{
							System.out.printf("%s %s %d %d %d \n",msg.getId(),arg,msg.getCurrentX(),msg.getCurrentY(),msg.getCurrentZ());
						}
					}
				}
			}
		}else{
			System.out.println("Error: please input at least one message ID");
		}
	}
	
	/**
	 * 读取signal_info.txt每一行数据到list(过滤掉空行)
	 */
	private static List<String> getFileInfo(){
		List<String> dataList = new ArrayList<String>();
		String filePath = "signal_info.txt";
//		String filePath = "D:\\workspace\\demo-maven\\src\\main\\java\\com\\hong\\signal_info.txt";
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			String data;
			while((data=br.readLine()) != null && !data.equals("")){
				dataList.add(data);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return dataList;
	}
	
	/**
	 * 把每一行的消息封装成Message对象
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static List<Message> getMessageList() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		List<String> dataList = getFileInfo();
		//Message字段，顺序需跟signal_info消息代表字段保持一致
		String[] fieldNames = {"id","lastX","lastY","lastZ","offsetX","offsetY","offsetZ"};
		List<Message> messageList = new ArrayList<Message>();
		for(String data : dataList){
			Message msg = new Message();
			String[] array = data.split(" ");
			for(int i=0; i<array.length; i++){
				String value = array[i];
				String filedName = fieldNames[i];
				//根据字段名称反射对msg对象赋值
				Field field = msg.getClass().getDeclaredField(filedName);
				field.setAccessible(true);
				if(i == 0){
					//无人机id
					field.set(msg,value);
				}else{
					//非整型坐标数据赋值为null
					field.set(msg,isInteger(value) ? Integer.valueOf(value) : null);
				}
			}
			messageList.add(msg);
		}
		return messageList;
	}
	
	/**
	 * 校验Message对象是否为故障状态，非故障状态，计算当前坐标
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static List<Message> checkData() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		List<Message> list = getMessageList();
		int index = 0;
		for(Message msg : list){
			Integer lastX = msg.getLastX();
			Integer lastY = msg.getLastY();
			Integer lastZ = msg.getLastZ();
			Integer offsetX = msg.getOffsetX();
			Integer offsetY = msg.getOffsetY();
			Integer offsetZ = msg.getOffsetZ();
			
			//校验是否故障，一旦故障，退出循环，后面数据不做处理（默认故障状态）
			if(null == lastX || null == lastY || null == lastZ){
				break;
			}
			if(index != 0 && (null == offsetX || null == offsetY || null == offsetZ)){
				break;
			}
			
			//非故障数据，变更状态，并计算当前坐标
			msg.setCrash(false);
			if(index == 0){
				msg.setCurrentX(lastX);
				msg.setCurrentY(lastY);
				msg.setCurrentZ(lastZ);
			}else{
				msg.setCurrentX(lastX + offsetX);
				msg.setCurrentY(lastY + offsetY);
				msg.setCurrentZ(lastZ + offsetZ);
			}
			index++;
		}
		
		return list;
	}
	
	/**
	 * 校验字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
