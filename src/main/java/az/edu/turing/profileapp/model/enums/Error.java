package az.edu.turing.profileapp.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Error {
    ERR_01("ERR_01", "Record was not found!"),
    ERR_02("ERR_02", "MailAddress or Password already exists!"),;

    private final String errorCode;
    private final String errorDescription;
}
