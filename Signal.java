
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
			//��ȡ������Ϣ
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
	 * ��ȡsignal_info.txtÿһ�����ݵ�list(���˵�����)
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
	 * ��ÿһ�е���Ϣ��װ��Message����
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static List<Message> getMessageList() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		List<String> dataList = getFileInfo();
		//Message�ֶΣ�˳�����signal_info��Ϣ�����ֶα���һ��
		String[] fieldNames = {"id","lastX","lastY","lastZ","offsetX","offsetY","offsetZ"};
		List<Message> messageList = new ArrayList<Message>();
		for(String data : dataList){
			Message msg = new Message();
			String[] array = data.split(" ");
			for(int i=0; i<array.length; i++){
				String value = array[i];
				String filedName = fieldNames[i];
				//�����ֶ����Ʒ����msg����ֵ
				Field field = msg.getClass().getDeclaredField(filedName);
				field.setAccessible(true);
				if(i == 0){
					//���˻�id
					field.set(msg,value);
				}else{
					//�������������ݸ�ֵΪnull
					field.set(msg,isInteger(value) ? Integer.valueOf(value) : null);
				}
			}
			messageList.add(msg);
		}
		return messageList;
	}
	
	/**
	 * У��Message�����Ƿ�Ϊ����״̬���ǹ���״̬�����㵱ǰ����
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
			
			//У���Ƿ���ϣ�һ�����ϣ��˳�ѭ�����������ݲ�������Ĭ�Ϲ���״̬��
			if(null == lastX || null == lastY || null == lastZ){
				break;
			}
			if(index != 0 && (null == offsetX || null == offsetY || null == offsetZ)){
				break;
			}
			
			//�ǹ������ݣ����״̬�������㵱ǰ����
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
	 * У���ַ����Ƿ�Ϊ����
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
