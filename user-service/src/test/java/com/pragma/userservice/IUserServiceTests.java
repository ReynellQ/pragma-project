package com.pragma.userservice;

import com.pragma.userservice.domain.api.IAuth;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.api.IUserValidator;
import com.pragma.userservice.domain.exception.IncorrectDataException;
import com.pragma.userservice.domain.model.Role;
import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.domain.useCase.UserUseCase;
import com.pragma.userservice.infrastructure.exception.UserNotFoundException;
import com.pragma.userservice.infrastructure.exception.UserConflictForEmailException;
import com.pragma.userservice.infrastructure.exception.UserConflictForIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class IUserServiceTests {
	IRolesPersistencePort rolesPersistencePort;

	IUserServicePort userServicePort;
	IUserPersistencePort persistencePort;
	IUserValidator userValidator;
	IAuth auth;
	/**
	 * Set up the userServicePort and their dependencies in mocks, in order to unit test UserUseCase
	 */
	@BeforeEach
	void setUp(){
		persistencePort = mock(IUserPersistencePort.class);
		userValidator = mock(IUserValidator.class);
		auth = mock(IAuth.class);
		rolesPersistencePort = mock(IRolesPersistencePort.class);
		userServicePort = new UserUseCase(persistencePort, rolesPersistencePort, userValidator, auth);
		//Mock data
		setUpPersistencePortMock();
		when(rolesPersistencePort.getRol(2)).thenReturn( new Role(2, "PROP", "Propietario"));
	}
	/**
	 * Mock the returns of userValidator when the methods are called.
	 */

	/**
	 * Mock the returns of persistencePort when the methods are called.
	 */
	private void setUpPersistencePortMock() {
		//Inserted data
		when(persistencePort.getUser(1l)).thenReturn(UserData.USER_001);
		when(persistencePort.getUser(2l)).thenReturn(UserData.USER_002);
		when(persistencePort.getUser(3l)).thenThrow(new UserNotFoundException());
		Answer<Void> answer = invocation -> {
			when(persistencePort.getUser(4l)).thenReturn(UserData.NON_INSERTED_USER_002);
			return null;
		};
		doAnswer(answer).when(persistencePort)
				.saveUser(UserData.NON_INSERTED_USER_002);
		doThrow( new UserConflictForIdException()).when(persistencePort)
				.saveUser(UserData.USER_001);
		doThrow( new UserConflictForIdException()).when(persistencePort)
				.saveUser(UserData.USER_002);
		doThrow( new UserConflictForIdException()).when(persistencePort)
				.saveUser(UserData.NON_INSERTED_USER_003);
		doThrow( new UserConflictForEmailException()).when(persistencePort)
				.saveUser(UserData.NON_INSERTED_USER_004);
		doNothing().when(persistencePort).saveUser(UserData.NON_INSERTED_USER_005);
		doNothing().when(persistencePort).saveUser(UserData.NON_INSERTED_USER_006);
		doNothing().when(persistencePort).saveUser(UserData.NON_INSERTED_USER_007);
	}

	@Test
	void getAnExistingUser() {
		User u1 = UserData.USER_001;
		Long id = u1.getId();
		//If the user is saved, can be obtained
		assertTrue(userServicePort.getUser(id).getId() == u1.getId());
	}

	@Test
	void getANonExistingUser() {
		User u1 = UserData.NON_INSERTED_USER_001;
		Long id = u1.getId();
		//If the user isn't saved, cannot be obtained and throws the UserDoesntExistsException.
		assertThrows(UserNotFoundException.class, () ->userServicePort.getUser(id));
	}

	/**
	 * Save a new owner. In this case, the method saveOwner show run without problems, and the service port can obtain
	 * the user that was inserted.
	 */
	@Test
	void saveANewOwner(){
		User u1 = UserData.NON_INSERTED_USER_002;
		when(userValidator.emailChecker(u1.getEmail())).thenReturn(true); //The email is valid
		when(userValidator.phoneChecker(u1.getPhone())).thenReturn(true); //The phone is valid
		assertDoesNotThrow(()->userServicePort.saveUser(u1)); //Insert the user
		//If it's saved, it can be obtained vía getUser(id)
		assertTrue(userServicePort.getUser(u1.getId()).getId() == u1.getId());
	}

	/**
	 * Save an existing owner. In this case, the method saveOwner should throw one of two exceptions:
	 * UserWithIDAlreadyExistsException, if a user with a same ID is located in the persistence layer;
	 * UserWithEmailAlreadyExistsException, if a user with a same email located in the persistence layer.
	 */
	@Test
	void saveAnExistingOwner(){
		User u1 = UserData.USER_001; //This user is inserted
		User u2 = UserData.NON_INSERTED_USER_004; //This user has the same email that USER_001
		when(userValidator.emailChecker(u1.getEmail())).thenReturn(true); //The email is valid
		when(userValidator.phoneChecker(u1.getPhone())).thenReturn(true); //The phone is valid
		when(userValidator.emailChecker(u2.getEmail())).thenReturn(true); //The email is valid
		when(userValidator.phoneChecker(u2.getPhone())).thenReturn(true); //The phone is valid
		assertThrows(UserConflictForIdException.class,
				()->userServicePort.saveUser(u1)); //Insert the user, but throws an exception
		assertThrows(UserConflictForEmailException.class,
				()->userServicePort.saveUser(u2)); //Insert the user, but throws an exception
	}

	/**
	 * Save a user with bad data. In this case, the method saveOwner should throw a IncorrectDataException. This bad
	 * data will be the email, the phone, and both together.
	 *
	 */
	@Test
	void saveAnOwnerWithBadData(){
		User u1 = UserData.NON_INSERTED_USER_005; //This user isn't inserted and their email is bad
		when(userValidator.emailChecker(u1.getEmail())).thenReturn(false); //The email isn't valid
		when(userValidator.phoneChecker(u1.getPhone())).thenReturn(true); //The phone is valid
		assertThrows(IncorrectDataException.class,
				()->userServicePort.saveUser(u1)); //Insert the user, but throws an exception


		User u2 = UserData.NON_INSERTED_USER_006; //This user isn't inserted and their phone is bad
		when(userValidator.emailChecker(u1.getEmail())).thenReturn(true); //The email is valid
		when(userValidator.phoneChecker(u1.getPhone())).thenReturn(false); //The phone isn't valid
		assertThrows(IncorrectDataException.class,
				()->userServicePort.saveUser(u2)); //Insert the user, but throws an exception

		User u3 = UserData.NON_INSERTED_USER_007; //This user isn't inserted and their phone is bad
		when(userValidator.emailChecker(u1.getEmail())).thenReturn(false); //The email is valid
		when(userValidator.phoneChecker(u1.getPhone())).thenReturn(false); //The phone isn't valid
		assertThrows(IncorrectDataException.class,
				()->userServicePort.saveUser(u3)); //Insert the user, but throws an exception
	}

}
