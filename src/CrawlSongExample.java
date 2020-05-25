import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlSongExample {
    public static void main(String[] args) {
        try {
            //Sử dụng URL của java.net để khởi tạo đường dẫn thư viện nhạc muốn lấy danh sách bài hát
            URL url = new URL("https://www.nhaccuatui.com/bai-hat/nhac-tre-moi.html");

            //Mở stream và đưa nó vào InputStreamReader
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            scanner.close();

            //Xóa tất cả các new line trong content lấy được
            //Phương thức replaceAll() trả về một chuỗi thay thế tất cả các chuỗi ký tự phù hợp với regex.
            content = content.replaceAll("\\n+", "");

            //Lọc nội dung content lấy được trong tag a chứa class name_song và
            // In ra danh sách tên bài hát lấy được theo điều kiện lọc

            /** name_song\">(.*?) :Bắt đầu từ name_song kết thúc bằng \"> đóng thẻ a, (.*?) bỏ bất kỳ
             * ký tự nào xuất hiện 0 hoặc nhiều lần hoặc xuất hiện 0 hoặc 1 lần
             * </a> :
             * group() Viết dưới dạng group, bởi dấu () vd: .compile("(name_song\">)(.*?)(</a>)")
             * */

            Pattern pattern = Pattern.compile("name_song\">(.*?)</a>");
            Matcher matcher1 = pattern.matcher(content);
            while (matcher1.find()) {
                System.out.println(matcher1.group(1));
//                System.out.println(content.substring(matcher.start(),matcher.end()));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
