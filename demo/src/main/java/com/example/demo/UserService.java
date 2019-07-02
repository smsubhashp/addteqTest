package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;


	/*
	 * public List<UserDTO> getUserNameFromFile() { List<UserDTO> l = new
	 * ArrayList<UserDTO>(); ClassLoader classLoader = getClass().getClassLoader();
	 * File f = new File(classLoader.getResource("usernames.txt").getFile());
	 * BufferedReader bufferedReader; try { bufferedReader = new BufferedReader(new
	 * FileReader(f)); String line = bufferedReader.readLine(); while (line != null)
	 * { UserDTO u = new UserDTO(); u.setUserName(line); l.add(u); line =
	 * bufferedReader.readLine(); } }catch(FileNotFoundException e){
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } return l; }
	 */

	public UserDTO getUserNameFromFile() throws IOError, FileNotFoundException { 
		UserDTO u =null;
		ClassLoader classLoader = getClass().getClassLoader(); 
		File f = new File(classLoader.getResource("usernames.txt").getFile()); 
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(f));
			String line = bufferedReader.readLine();
			while (line != null) {
				u = new UserDTO();
				u.setUserName(line);
				line = bufferedReader.readLine();
			}
		}catch(FileNotFoundException e){
			throw new FileNotFoundException();
		} catch (IOException e) {
			throw new IOError();
		}finally {
			if(bufferedReader !=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new IOError();
				}
			}
		}
		return u;
	}



	/*
	 * public List<UserDTO> saveUserNameToDB() { List<UserDTO> l =
	 * getUserNameFromFile(); for(UserDTO u : l) { UserEntity userEntity = new
	 * UserEntity(); userEntity.setUsername(u.getUserName()); UserEntity u2 =
	 * userRepository.save(userEntity); UserDTO UserDTO2 = new UserDTO();
	 * UserDTO2.setUserName(u2.getUsername()); l.add(UserDTO2); } return l; }
	 */

	public UserDTO saveUserNameToDB() throws IOError, FileNotFoundException {
		UserDTO userDTO = getUserNameFromFile();
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(userDTO.getUserName());
		UserEntity saveEntity = userRepository.save(userEntity);
		UserDTO savaDto = new UserDTO();
		savaDto.setUserName(saveEntity.getUsername());
		return savaDto;
	}



	public List<UserDTO> getAllUserNameFromDB() {
		
		 Iterable<UserEntity> it = userRepository.findAll();
		 List<UserDTO> l = new ArrayList<UserDTO>();
		 for(UserEntity u: it) {
			 UserDTO userDTO = new UserDTO();
			 userDTO.setUserName(u.getUsername());
			 l.add(userDTO);
		 }
		 return l;
	} 


//spring.jpa.database=zcxfobqy

}
