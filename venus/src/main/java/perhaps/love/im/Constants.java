package perhaps.love.im;

import java.util.regex.Pattern;

public class Constants {

    public static final String COOKIE_LOGIN = "im_uid";
    
    public static final String COOKIE_TOKEN = "im_tk";
    
    public static final String DEFAULT_AVATAR = "http://a1.itc.cn/sceapp/focus_static/passport/images/default_avatar.jpg";
    
    public static final int QC_APP_ID = 1253232793;
    
    public static final String QC_SECRET_ID = "AKIDqfJm4CRBvUs44vp6QaZO2XtDQVQBEGai";
    
    public static final String QC_SECRET_KEY = "LpHXcVDwrgP1X1Pgv474pPo6FD1eAXr1";
    
    public static final String QC_BUCKET = "imimg";
    
    public static final Pattern DISPLAY_NAME_PTN = Pattern.compile("\\s|(\\(.*\\))");

}
