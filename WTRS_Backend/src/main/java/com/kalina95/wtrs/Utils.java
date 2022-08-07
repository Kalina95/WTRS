package com.kalina95.wtrs;

import com.kalina95.wtrs.employee.CompanyRole;
import com.kalina95.wtrs.user.SystemRole;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Strings.isNullOrEmpty;

public class Utils {

    public Date stringToDateParser(String dateAsString) {

        if (!isNullOrEmpty(dateAsString)) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else return null;
    }


    public SystemRole checkSystemRole(String roleAsString) {
        if (!isNullOrEmpty(roleAsString)) {
            if (roleAsString.equals("ROLE_ADMIN") || roleAsString.equals("ADMIN")) return SystemRole.ROLE_ADMIN;
            if (roleAsString.equals("ROLE_USER") || roleAsString.equals("USER")) return SystemRole.ROLE_ADMIN;
        }
        return null;
    }

    public CompanyRole checkCompanyRole(String roleAsString) {
        if (!isNullOrEmpty(roleAsString)) {
            if (roleAsString.equals("ROLE_DIRECTOR") || roleAsString.equals("DIRECTOR")) return CompanyRole.DIRECTOR;
            if (roleAsString.equals("ROLE_MANAGER") || roleAsString.equals("MANAGER")) return CompanyRole.MANAGER;
            if (roleAsString.equals("ROLE_EMPLOYEE") || roleAsString.equals("EMPLOYEE")) return CompanyRole.EMPLOYEE;
        }
        return null;
    }
}
