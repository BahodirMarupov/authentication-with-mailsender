package uz.themind.springauthentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.themind.springauthentication.exception.EmailAlreadyExistsException;
import uz.themind.springauthentication.model.domen.ConfirmationToken;
import uz.themind.springauthentication.model.domen.UserPrincipal;
import uz.themind.springauthentication.model.enums.UserRole;
import uz.themind.springauthentication.model.request.RegistrationRequest;
import uz.themind.springauthentication.model.response.ResponseData;
import uz.themind.springauthentication.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    private final static Logger logger = LoggerFactory.getLogger(AuthService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email %s is not found!", email)));
    }

    @Transactional
    public ResponseData<?> saveUser(RegistrationRequest request) {

        ResponseData<String> responseData = new ResponseData<>();
        boolean present = userRepository.findByEmail(request.getEmail()).isPresent();

        if (present) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        if (!request.getPassword().equals(request.getPrePassword())) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST, "Confirmation password cannot match!", new Date());
        }

        try {
            UserPrincipal savedUser = userRepository.save(request.mapToUserPrincipal(UserRole.USER, passwordEncoder.encode(request.getPassword()), false, false));

            String token = UUID.randomUUID().toString();

            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    savedUser
            );
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            responseData.setData(token);

        } catch (Exception exception) {
            logger.error(exception.getMessage());
            responseData.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseData.setTimestamp(new Date());
            responseData.setMessage("Registration failed!");
            return responseData;
        }

        responseData.setCode(HttpStatus.OK);
        responseData.setSuccess(true);
        responseData.setTimestamp(new Date());
        responseData.setMessage("Registered successfully!");
        return responseData;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

}
