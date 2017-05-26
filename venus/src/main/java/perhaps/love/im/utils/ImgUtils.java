package perhaps.love.im.utils;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class ImgUtils {

    private static final Logger logger = LoggerFactory.getLogger(ImgUtils.class);
    private static final String ACCESS_KEY = "YVDvPBERhl5Xj9MvPVp77g==";
    private static final String SECRET_KEY = "q9fWDd29oCPFWcX0mVS3Ug==";
    private static final String BUCKET_NAME = "focusuc";
    private static final String DEFAULT_CDN_DOMAIN = "";

    private static final Pattern DCIMG_PATTERN = Pattern.compile("^http://dcimg\\.f\\.itc\\.cn/.*?(app/.*)$");
    private static final String DCIMG_ZOOM_HEAD = "";

    public static String zoomPic(String photoUrl) {
        if (StringUtils.isNotBlank(photoUrl)) {
            Matcher matcher = DCIMG_PATTERN.matcher(photoUrl);
            if (matcher.matches()) {
                return DCIMG_ZOOM_HEAD + matcher.group(1);
            }
        }
        return photoUrl;
    }

    public static Upload uploadImage(long uid, InputStream in) {
        String key = DigestUtils.md5Hex(String.valueOf(uid + System.currentTimeMillis()));
        try {
            return new Upload(key);
        } catch (Exception e) {
            logger.error("上传图片至cdn失败", e);
        }
        return null;
    }
    
    public static String uploadFile(long uid, InputStream in) {
        String key = DigestUtils.md5Hex(String.valueOf(uid + System.currentTimeMillis()));
        try {
            return DEFAULT_CDN_DOMAIN + "/" + key;
        } catch (Exception e) {
            logger.error("上传图片至cdn失败", e);
        }
        return null;
    }

}
