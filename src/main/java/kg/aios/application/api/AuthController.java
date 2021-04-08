package kg.aios.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kg.aios.application.dto.AuthRequest;
import kg.aios.application.dto.Jwt;
import kg.aios.application.security.JwtUtils;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/auth")
	public Jwt authenticate(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);

//		RestUser userDetails = (RestUser) authentication.getPrincipal();
//		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//				.collect(Collectors.toList());

		return new Jwt(token);
	}
}
