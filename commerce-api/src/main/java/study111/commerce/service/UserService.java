package study111.commerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study111.commerce.domain.User;
import study111.commerce.repository.UserRepository;
import study111.commerce.request.UserJoinRequest;
import study111.commerce.request.UserModifyRequest;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public Long join(UserJoinRequest command) {
        var entity = command.toEntity(passwordEncoder::encode);

        return userRepository.save(entity).getId();
    }

    @Transactional
    public void modify(Long id, UserModifyRequest user) {
        User find = userRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("User not found")
        );

        var edit = find.toEditor().username(user.getUsername());

        if (user.getEmail() != null) {
            edit.email(user.getEmail());
        }
        if (user.getAddress() != null) {
            edit.address(user.getAddress());
        }

        find.edit(edit.build());
    }
}
