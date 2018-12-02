package sachonline;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sachonline.data.entities.*;
import sachonline.data.services.GioHangService;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatsachonlineApplication.class)
public class DatsachonlineApplicationTests {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional(noRollbackFor = Exception.class)
    public void TestCrud() {
//		KhuyenMai khuyenMai = new KhuyenMai();
//		khuyenMai.setMoTa("Đây là cái mô tả");
//		entityManager.persist(khuyenMai);
//		entityManager.flush();

        // 1
        addSachAndTacGia();
        // 2
        addHoaDonNhapSach();
        // 3
        addHoaDonBanSach();
    }

    private void addHoaDonBanSach() {
        DiaChi diaChi = addDiaChi(EnumDiaChi.DIA_CHI_CHUNG_CU, "Xuân La");

        // Hóa đơn
        HoaDon hoaDon = new HoaDon();
        hoaDon.setDienThoai("01684856536");
        hoaDon.setMoTa("Hóa đơn này là hóa đơn bán sách");
        hoaDon.setTenNguoiNhan("Chủ hiệu sách");

        hoaDon.setNgayGiaoToi(Timestamp.valueOf(LocalDateTime.now().plusDays(5)));

        // Sách nhập
        SachNhapBan sachNhapBan = new SachNhapBan();
        sachNhapBan.setHoaDon(hoaDon);
        sachNhapBan.setLoaiDon(true);// False là nhập, True là bán
        sachNhapBan.setSoLuong(10);

        // Lấy sách muốn nhập trong database
        Sach sachNhap = entityManager.find(Sach.class, 2L);

        // Nhập vào sách muốn nhập
        sachNhapBan.setSachNhapBan(sachNhap);


        // Nhập xong thành tiền
        hoaDon.setThanhTien(sachNhap.getGiaSachNhap() * sachNhapBan.getSoLuong());
        hoaDon.setDiaChi(diaChi);

        // Lưu vào trong database
        entityManager.persist(diaChi);
        entityManager.persist(hoaDon);
        entityManager.persist(sachNhapBan);

        entityManager.flush();
    }

    private DiaChi addDiaChi(EnumDiaChi diaChiChungCu, String s) {
        DiaChi diaChi = new DiaChi();
        diaChi.setLoaiDiaChi(diaChiChungCu.toString());
        diaChi.setTinhThanhPho("Hà Nội");
        diaChi.setQuanHuyen("Bắc Từ Liêm");
        diaChi.setPhuongXa(s);
        diaChi.setChiTietDiaChi("Đường ABC Ngách Z Ngõ Y Số Nhà X");
        return diaChi;
    }

    private void addHoaDonNhapSach() {
        // Địa chỉ
        DiaChi diaChi = addDiaChi(EnumDiaChi.DIA_CHI_NHA_RIENG, "Xuân Tảo");

        // Hóa đơn
        HoaDon hoaDon = new HoaDon();
        hoaDon.setDienThoai("01684856536");
        hoaDon.setMoTa("Hóa đơn này là hóa đơn nhập sách");
        hoaDon.setTenNguoiNhan("Hoàng Bùi");
        // Ngày đặt là ngày hôm tạo | Auto
//        hoaDon.setNgayDat(Timestamp.valueOf(LocalDateTime.now()));
        // Cộng thêm 10 hôm cho ngày nhận
        hoaDon.setNgayGiaoToi(Timestamp.valueOf(LocalDateTime.now().plusDays(10)));

        // Sách nhập
        SachNhapBan sachNhapBan = new SachNhapBan();
        sachNhapBan.setHoaDon(hoaDon);
        sachNhapBan.setLoaiDon(false);// False là nhập, True là bán
        sachNhapBan.setSoLuong(20);

        // Lấy sách muốn nhập trong database
        Sach sachNhap = entityManager.find(Sach.class, 1L);

        // Nhập vào sách muốn nhập
        sachNhapBan.setSachNhapBan(sachNhap);


        // Nhập xong thành tiền
        hoaDon.setThanhTien(sachNhap.getGiaSachNhap() * sachNhapBan.getSoLuong());
        hoaDon.setDiaChi(diaChi);

        //
        entityManager.persist(diaChi);
        entityManager.persist(hoaDon);
        entityManager.persist(sachNhapBan);

        entityManager.flush();
    }

    public void addSachAndTacGia() {
        TacGia tacGia = new TacGia();
        tacGia.setTenTacGia("Du Phong");

        TacGia tacGia1 = new TacGia();
        tacGia1.setTenTacGia("An Khánh Nhung");

        TacGia tacGia2 = new TacGia();
        tacGia2.setTenTacGia("Colleen Mccullough");

        TacGia tacGia3 = new TacGia();
        tacGia3.setTenTacGia("Dale Carnegie");

        TacGia tacGia4 = new TacGia();
        tacGia4.setTenTacGia("Vũ Ngọc Đĩnh");

        TacGia tacGia5 = new TacGia();
        tacGia5.setTenTacGia("Nhiều Tác Giả");

        TacGia tacGia6 = new TacGia();

        TacGia tacGia7 = new TacGia();
        tacGia7.setTenTacGia("Gomi Taro");

        TacGia tacGia8 = new TacGia();
        tacGia8.setTenTacGia("Gomi Taro");

        TacGia tacGia9 = new TacGia();
        tacGia9.setTenTacGia("Gosho Aoyama");

        Sach sach = new Sach();
        Sach sach1 = new Sach();
        Sach sach2 = new Sach();
        Sach sach3 = new Sach();
        Sach sach4 = new Sach();
        Sach sach5 = new Sach();
        Sach sach6 = new Sach();
        Sach sach7 = new Sach();
        Sach sach8 = new Sach();
        Sach sach9 = new Sach();

        sach.setNhaXuatBan("Nhà Xuất Bản Văn Hóa - Văn Nghệ");
        sach1.setNhaXuatBan("Nhà Xuất Bản Thanh Niên");
        sach2.setNhaXuatBan("Nhà Xuất Bản Văn Học");
        sach3.setNhaXuatBan("Nhà Xuất Bản Tổng hợp TP.HCM");
        sach4.setNhaXuatBan("Nhà Xuất Bản Văn Học");
        sach5.setNhaXuatBan("Nhà Xuất Bản Thanh Hóa");
        sach6.setNhaXuatBan("Photo Essay “ Hậu Duệ Mặt Trời” (Tặng Kèm 6 Poscard Có Chữ Kí Và 1 Poster Ảnh)");
        sach7.setNhaXuatBan("Nhà Xuất Bản Kim Đồng");
        sach8.setNhaXuatBan("Nhà Xuất Bản Kim Đồng");
        sach9.setNhaXuatBan("Nhà Xuất Bản Kim Đồng");


        sach.setGiaSachBan(80000D);
        sach1.setGiaSachBan(270000D);
        sach2.setGiaSachBan(11000D);
        sach3.setGiaSachBan(76000D);
        sach4.setGiaSachBan(200000D);
        sach5.setGiaSachBan(340000D);
        sach6.setGiaSachBan(219000D);
        sach7.setGiaSachBan(25000D);
        sach8.setGiaSachBan(25000D);
        sach9.setGiaSachBan(25000D);

        sach.setGiaSachNhap(56000D);
        sach1.setGiaSachNhap(200000D);
        sach2.setGiaSachNhap(110000D);
        sach3.setGiaSachNhap(50000D);
        sach4.setGiaSachNhap(150000D);
        sach5.setGiaSachNhap(280000D);
        sach6.setGiaSachNhap(150000D);
        sach7.setGiaSachNhap(20000D);
        sach8.setGiaSachNhap(20000D);
        sach9.setGiaSachNhap(20000D);

        sach.setTiLeKhuyenMai((short) 10);
        sach1.setTiLeKhuyenMai((short) 10);
        sach2.setTiLeKhuyenMai((short) 30);
        sach3.setTiLeKhuyenMai((short) 30);
        sach4.setTiLeKhuyenMai((short) 20);
        sach5.setTiLeKhuyenMai((short) 30);
        sach6.setTiLeKhuyenMai((short) 30);
        sach7.setTiLeKhuyenMai((short) 0);
        sach8.setTiLeKhuyenMai((short) 0);
        sach9.setTiLeKhuyenMai((short) 20);


        sach.setGioiThieu("Thanh Xuân Không Hối Tiếc\n" +
                "\n" +
                "Mỗi người có một cách khác nhau để sống những ngày tuổi trẻ, có người dành trọn nó cho những cuộc tình, có người dành trọn nó cho công việc, có người dành trọn nó để tự yêu thương mình, và cũng có những người chia tuổi trẻ của mình ra, để yêu một vài người, sau đó yêu mình, yêu người xung quanh mình, rồi đến một lúc nào đó thích hợp mới tiếp tục muốn yêu thêm một người cho đến hết cuộc đời.\n" +
                "\n" +
                "Dù người ta có dành tuổi trẻ của mình cho ai hay để làm gì, thì cũng mong sau này khi đã đủ chín chắn để ngoái đầu nhìn lại, họ cũng sẽ mỉm cười, một nụ cười vô ưu viên mãn.\n" +
                "\n" +
                "“Không có giọt nước mắt nào rơi\n" +
                "\n" +
                "Vì những thứ, những người không xứng đáng.\n" +
                "\n" +
                "Không có nỗi buồn nào vô hạn\n" +
                "\n" +
                "Để ủ dột thê lương mọc kín góc tâm hồn!”\n" +
                "\n" +
                "Cái người ta hoài công tìm kiếm suốt một thời xanh trẻ, rốt cuộc không phải là một tình yêu điên cuồng mù quáng, lại càng không phải là những thứ vật chất phù du. Cuối cùng khi đi hết đoạn đường đầy tin yêu và khát vọng, người ta chỉ mong thấy được sự thanh thản bình yên trong sâu thẳm lòng mình.\n" +
                "\n" +
                "Chúc bạn có một thanh xuân không hối tiếc!\n" +
                "\n" +
                "- Du Phong");

        sach1.setGioiThieu("Dạy Con Học Nói\n" +
                "\n" +
                "Là một giáo viên, hàng ngày tiếp xúc với các con, cô Nhung có những trải nghiệm sâu sắc trong lĩnh vực Dạy con học nói. Vì vậy, cô Nhung đã tập hợp trong cuốn sách tụyệt vời này những bài học hữu ích và giá trị nhất.\n" +
                "\n" +
                "Với mong ước tất cà mọi người đều có thể chung tay góp phần hỗ trợ cho trẻ em đặc biệt (trẻ chậm nói, tự kỷ,...) thông qua cuốn sách vô cùng dễ hiểu, dễ học dễ dạy này, để thực hiện sứ mệnh của mình.");
        sach2.setGioiThieu("Tiếng Chim Hót Trong Bụi Mận Gai\n" +
                "\n" +
                "Tiểu thuyết Tiếng chim hót trong bụi mận gai xuất bản vào mùa xuân 1977 cùng một lúc ở New York, San Francisco, London, Sydney - Ít lâu sau đã được dịch ra bảy thứ tiếng, được bạn đọc nhiệt liệt hoan nghênh và giới phê bình đánh giá cao. Suốt mấy năm là tác phẩm ăn khách nhất ở phương Tây. Đây là tác phẩm đặc sắc, có giá trị trong văn học thế giới hiện đại.\n" +
                "\n" +
                "Colleen McCulough không phải là nhà văn chuyên nghiệp, trước đó hầu như không ai biết tiếng. Khi tiểu thuyết Tiếng chim hót trong bụi mận gai đem lại vinh dự cho tác giả thì McCulough vẫn chỉ là một nhân viên y tế bình thường. Bà sinh ở Úc, bang New South Wales, trong gia đình công nhân xây dựng xuất thân từ Ireland. Thời thanh xuân McCulough ở Sydney, đã từng học trường của nhà thờ công giáo, từ bé – bà mơ ước trở thành bác sĩ, nhưng không có điều kiện để qua đại học y. Bà đã thử làm một số nghề- làm báo, công tác thư viện, dạy học rồi trở lại nghề y, qua lớp đào tạo chuyên môn về sinh lý học thần kinh. Sau đó, bà đã làm việc bà đã làm việc tại các bệnh viện ở Sydney, London, Birmingham, rồi sang Mỹ, làm việc tại một trường y thuộc trường đại học Yale. Năm 1974 bà viết cuốn tiểu thuyết đầu tiên, không có tiếng vang gì. Tiếng chim hót trong bụi mận gai được thai nghén trong ngót bốn năm, rồi đầu mùa hè 1975, bà bắt tay vào viết liền một mạch trong mười tháng. Suốt thời gian ấy, bà vẫn bận túi bụi công việc ở bệnh viện, chỉ viết tác phẩm vào ban đêm và ngày chủ nhật.\n" +
                "\n" +
                "Tác phẩm này có thể gọi là “Xaga về gia đình Cleary”. Xaga là hình thức văn xuôi cổ có tính anh hùng ca, kể chuyện một cách điềm đạm về những con người hùng dũng. Cuốn tiểu thuyết này viết về lịch sử nửa thế kỷ của ba thế hệ một gia đình lao động - gia đình Cleary. Loại tiểu thuyết lịch sử gia đình từ trước đã có những thành công như thiên sử thi vè dòng họ Foocxaitơ của Gônxuocthy, “Gia đình Tibô” của Rôgiê Mactanh duy Gar. “Gia đình Artamônôp” của M. Gorki. Đặc điểm chung của các tác phẩm đó là số phận gia đình tiêu biểu cho số phận của giaia cấp tư sản, các thế hệ sau đoạn tuyệt với truyền thống của gia đình. So sánh với những tác phẩm kể trên thì tác phẩm của McCulough có sự khác biệt, có cái độc đáo riêng của nó. Trước hết, đây là lịch sử của một gia đình lao động. Sự phát triển, tính kế thừa và đổi mới của ba thế hệ gia đình này là hình mẫu thu nhỏ của lịch sử dân tộc. Các thế hệ sau kế thừa những nét tốt đẹp nhất của gia đình - tính cần cù, tự chủ, tính kiên cường đón nhận những đòn ác liệt của số phận, lòng tự hào gia đình, song đồng thời có những đổi khác nhịp bước với thời đại. Nếu Fiona gan góc chịu đựng mọi tai họa nhưng cam chịu số phận thì con gái bà là Meggie đã tìm cách cướp lấy hạnh phúc của mình từ tay Chúa trời, và Jaxtina, con gái của Meggie là một cô gái hiện đại, có những chuẩn mực đạo đức hoàn toàn khác. Cuốn tiểu thuyết xây dựng như truyện sử biên gia đình, tác giả tập trung vào những xung đột tâm lý - đạo đức nhiều hơn là những vấn đề giai cấp - xã hội. Các nhân vật tuy vẫn chịu ảnh hưởng của hoàn cảnh, nhưng chủ yếu là ứng xử theo tính cách riêng của mình nhiều hơn. Trong số nhiều nhân vật, nổi bật hơn cả là ba nhân vật - Fiona, Meggie - con gái bà và cha đạo Ralph de Bricassart. Meggie có thể coi là nhân vật trung tâm của tác phẩm. Trong tiểu thuyết có nhiều tuyến tình tiết, nhiều môtíp, đề tài, song tất cả đều phục vụ câu chuyện chính: mối tình lớn lao trong sáng của Meggie và cha de Bricassart.\n" +
                "Trong tác phẩm quy mô lớn này, những xung đột tâm lý - tinh thần của nhân vật quyện chặt với sự miêu tả tỉ mỉ toàn cảnh lịch sử, địa lý, thiên nhiên. Thiên nhiên bao la, dữ dội nhưng có cái đẹp hoang sơ riêng của nó như hiện ra trước mắt bạn đọc. Giá trị nhận thức của tác phẩm do đó càng thêm đầy đủ. Mối quan hệ giữa thiên nhiên và con người ở đây mang tính chất chung sống hài hòa, thiên nhiên chưa bị uy hiếp đến nguy cơ hủy diệt.\n" +
                "\n" +
                "Tính hiện thực và lãng mạn trong tác phẩm này hòa lẫn vào nhau tới mức nhuần nhị. Sự miêu tả tỉ mỉ bằng hình thức của bản thân đời sống, cả từ cách ăn mặc, lời ăn tiếng nói của nhân vật, cho đến cách xén lông cừu, nếp sống hàng ngày..., lối kể chuyện thong thả theo trình tự thời gian khiến cho tác phẩm gần với loại tiểu thuyết hiện thực thế kỷ 19. Nhưng những tính cách phi thường rực rỡ biểu hiện trong những biến cố đột ngột, đầy hấp dẫn tạo nên màu sắc lãng mạn rất rõ nét. Môt tác phẩm văn học Mỹ thời nay xa lạ với những cảnh hung bạo, với “sex”, với “phản nhân vật” đưa bạn đọc trở về với những vấn đề “nhà” (theo nghĩa quê hương), “cội nguồn”, “cha và con” mà lại được ham chuộng như thế ở phương Tây thì đó chứng tỏ những vấn đề muôn thuở của nhân loại bao giờ cũng làm rung động lòng người ở bất cứ nơi nào trên hành tinh chúng ta.");
        sach3.setGioiThieu("Tại sao Đắc Nhân Tâm luôn trong Top sách bán chạy nhất thế giới?\n" +
                "\n" +
                "Bởi vì đó là cuốn sách mọi người đều nên đọc.\n" +
                "\n" +
                "Hiện nay có một sự hiểu nhầm đã xảy ra. Tuy Đắc Nhân Tâm là tựa sách hầu hết mọi người đều biết đến, vì những danh tiếng và mức độ phổ biến, nhưng một số người lại “ngại” đọc. Lý do vì họ tưởng đây là cuốn sách “dạy làm người” nên có tâm lý e ngại. Có lẽ là do khi giới thiệu về cuốn sách, người ta luôn gắn với miêu tả đây là “nghệ thuật đối nhân xử thế”, “nghệ thuật thu phục lòng người”… Những cụm từ này đã không còn hợp với hiện nay nữa, gây cảm giác xa lạ và không thực tế.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Nhưng đâu phải thế, Đắc Nhân Tâm là cuốn sách không hề lỗi thời! \n" +
                "\n" +
                "Những vấn đề được chỉ ra trong đó đều là căn bản ứng xử giữa người với người. Nếu diễn giải theo từ ngữ bây giờ, có thể gọi đây là “giáo trình” giúp hiểu mình – hiểu người để thành công trong giao tiếp. Có ai sống mà không cần giao tiếp? Có bao nhiêu người ngày ngày mệt mỏi, khổ sở vì gặp phải các vấn đề trong giao tiếp? Vì thế, Đắc Nhân Tâm chính là cuốn sách dành cho mọi người. Con cái nên đọc – cha mẹ càng nên đọc, nhân viên nên đọc – sếp càng nên đọc, người quen nhau nên đọc – người lạ nhau càng nên đọc…. Và đó mới chính thật là lý do Đắc Nhân Tâm luôn lọt vào Top sách bán chạy nhất thế giới, dù đã ra đời cách đây gần 80 năm.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Có lẽ sẽ có người vừa đọc vừa nghĩ, mấy điều trong sách này đơn giản mà, ai chẳng biết? Đúng thế, vì toàn bộ đều là những quy tắc, những cách cư xử căn bản giữa chúng ta với nhau thôi. Kiểu như “Không chỉ trích, oán trách hay than phiền”, “Thành thật khen ngợi và biết ơn người khác”, “Thật lòng làm cho người khác thấy rằng họ quan trọng”… Những điều này đúng thật là ai cũng biết, nhưng bạn có chắc bạn nhớ được và làm được những điều đơn giản đó? Vì vậy, cuốn sách mới ra đời, để giúp bạn thực hành.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Nhưng có lẽ đa số người đọc sẽ thành thật gật gù đồng ý với từng trang sách. Ồ nếu như bình tâm suy xét lại mọi việc, thì trong bất cứ trường hợp nào mình cũng có thể cư xử đúng mực, không làm người khác tổn thương, giúp bầu không khí luôn thoải mái, và thế là cả hai bên đều vui vẻ, công việc cũng vì thế mà suôn sẻ, thành công hơn. Vậy chứ mà cũng không dễ, bởi “cái tôi” của mỗi người thường chiến thắng tâm trí trong đa số trường hợp. Để thỏa mãn nó, chúng ta hay mắc sai lầm không đáng. Đó cũng chính là lý do Đắc Nhân Tâm có mặt, để nhắc nhở và từng chút giúp ta uốn nắn chính “cái tôi” của mình.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Với giọng văn giản dị, cách trình bày gần gũi nhưng cực kỳ khoa học bằng cách đúc rút những điều mối chốt ở cuối chương, Đắc Nhân Tâm là cuốn sách hiếm hoi không kén chọn người đọc. Bất cứ ai cũng có thể đồng cảm. Đây là công trình tâm huyết cả đời của Ngài Dale Carnegie, xuất phát từ chính nhu cầu của Dale khi cảm thấy cuộc đời mình sẽ không phạm phải quá nhiều sai lầm đã qua nếu như được học tử tế về cách cư xử trong cuộc sống. Ông đã viết bằng chính trải nghiệm phong phú cả đời mình. Thậm chí ông còn thuê cả một nhà nghiên cứu chuyên nghiệp để tìm và cùng ông nghiên cứu các tài liệu liên quan. Và cuốn sách hữu ích đến mức vừa ra đời đã phải tái bản liên tục, trở thành hiện tượng chưa từng có trong bối cảnh xuất bản ảm đạm của nước Anh lúc đó. Và mãi đến bây giờ.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Từ ngày ấy, Đắc Nhân Tâm trở thành cuốn sách không-chịu-nằm-yên-trên-kệ. Ngoài ý được tái bản liên tục ở khắp nơi trên thế giới, thì cụm từ này còn có một ý khác, đó là cuốn sách đã được Ngài Dale bổ sung và hiệu chỉnh liên tục các câu chuyện mới, cách diễn đạt mới, nên ở mỗi lần xuất hiện, Đắc Nhân Tâm đều có những điều mới mẻ. Việc này cũng tiếp tục được con trai ông thực hiện sau khi ông qua đời, như ý nguyện của ông.\n" +
                "\n" +
                "Được đánh giá là cuốn sách có sức lan tỏa rộng lớn, được dịch ra hầu hết các ngôn ngữ trên thế giới và luôn nằm trong Top sách bán chạy ở mọi thị trường xuất bản, Đắc Nhân Tâm đã có đời sống xứng tầm với giá trị thực tế của mình. Đây có thể coi là một trong những cuốn sách dòng self-hepl chính thống đầu tiên. Và Ngài Dale cũng trở thành một trong những tác giả ảnh hưởng trực tiếp nhiều nhất đến sự thay đổi tích cực của hàng triệu độc giả trên thế giới.");

        sach4.setGioiThieu("Hào Kiệt Lam Sơn\n" +
                "\n" +
                "Mười năm (1418 - 1428) nằm gai nếm mật ở rừng núi Chí Linh, Lê Lợi quy tụ biết bao hào kiệt, tích trữ lương thực, rèn đúc khí giới và cùng nhau ngày đêm giũa chí tạo lực, bàn mưu tính kế để dựng cờ khởi nghĩa đánh đuổi giặc Minh xâm lăng. Dù phải đến ba lần binh bại, quân tàn rút về cứ địa Chí Linh để củng cố lực lượng, nhưng sau đó, với mưu thần Nguyễn Trãi, người anh hùng áo vải Lam Sơn - Lê Lợi - dựa vào địa thế núi rừng, dàn binh bố trận tiến thẳng ra Nghệ An với khí thế ngất trời buộc Vương Thông, tướng nhà Minh phải cố thủ chờ viện binh để rồi cuối cùng với truyền thống nhân ái sáng ngời của dân tộc Việt, kẻ thù bại trận vẫn bình yên lui về cố quốc.\n" +
                "\n" +
                "Dựa vào những sự kiện, nhân vật nổi bật của một giai đoạn lịch sử Việt Nam kiêu hùng, tác giả Vũ Ngọc Đĩnh đã miêu tả, phác họa một bức tranh sinh động, đầy tình tiết và sự kiện hấp dẫn người đọc.");
        sach5.setGioiThieu("Chư Tử Mưu Lược Tung Hoành 1 (Bộ 5 Cuốn)\n" +
                "\n" +
                "Bộ sách Chư Tử Mưu Lược Tung Hoành quyển 1 gồm 5 cuốn:\n" +
                "\n" +
                "- Khổng Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "- Mạnh Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "- Mặc Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "- Lão Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "- Trang Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "Khổng Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "Ở Trung Quốc, Khổng Tử được gọi là bậc “thánh nhân”, người đời khoác cho ông rất nhiều vòng nguyệt quế, nhưng chủ yếu vẫn là nhà tư tưởng và nhà giáo dục. Và đặc biệt nhất là hậu thế vẫn tiếp nối nhau ngưỡng phục và vận dụng nội dung học thuyết chủ yếu của Khổng Tử trong Luận ngữ để đưa vào thực tế một cách thành công. Điều đó không chỉ khẳng định Khổng Tử là nhà tư tưởng, nhà giáo dục uyên bác mà còn nêu bật rằng Khổng Tử đồng thời còn là một nhà mưu lược xuất sắc.\n" +
                "\n" +
                "Sách Khổng Tử mưu lược tung hoành” nhấn mạnh với tư cách  là người sáng lập Nho gia, phạm trù và khái niệm cơ bản trong học thuyết của Khổng Tử đề cập mọi phương diện từ đạo đức, luân lý, chính trị,nhà sách online xã hội, giáo dục tư tưởng đến lập thân xử thế và trị nước. Tất cả những nội dung nêu trên đều có ảnh hưởng to lớn và sâu sắc đến sự phát triển của văn hóa tư tưởng dân tộc Trung Hoa nói riêng và nhiều nước phương Đông ngày nay.\n" +
                "\n" +
                "Mạnh Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "Mạnh Tử từng dùng sở học du thuyết chư hầu, đến đâu ông cũng được kính trọng, có thời gian làm khách của Tề Tuyên Vương . Nhưng cuối cùng, vì thấy không được trọng dụng nên lui về mở trường nhận học trò dạy học; ngồi luận đàm đạo lý với các cao đồ Vạn Chương, Công Tôn Xá … viết sách lập thuyết để truyền cho đời sau, đó chính là sách “Mạnh Tử” gồm có 7 thiên.\n" +
                "\n" +
                "Về căn bản, trí tuệ mưu lược của Mạnh Tử ẩn trong các lời bàn của ông về sách lược chính trị, mưu lược bảo vệ dân, đạo tu dưỡng, phương pháp giáo dục. Nói chung, phần lớn Mạnh Tử trình bày tư tưởng hàm chứa nhân sinh quan và đạo làm người: vận dụng thế nào cho phù hợp và hiệu quả từ chính trong cuộc sống.\n" +
                "\n" +
                "Sách Mạnh Tử mưu lược tung hoành tập trung trình bày tư tưởng mưu lược của ông thông qua tác phẩm “Mạnh Tử”, được minh họa bằng những câu truyện cổ kim với mục đích có thể đóng góp một khía cạnh nào đó trong việc nghiên cứu Mạnh Tử.\n" +
                "\n" +
                "Mặc Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "Thiên “Hiển học” sách Hàn Phi Tử viết: “Hiển học ở đời có Nho và Mặc”. Thời Xuân Thu – Chiến Quốc chứng kiến sự nảy nở của nhiều trường phái tư tưởng khác nhau (“trăm nhà đua tiếng”) sánh vai với Nho học của Khổng Tử, trong đó có Mặc học do Mặc Tử khai sáng.\n" +
                "\n" +
                "Mặc Tử là nhà tư tưởng, nhà giáo dục, nhà khoa học, nhà quân sự nổi tiếng trong lịch sử cổ đại. Những tư tưởng do ông đề xướng không chỉ đóng vai trò thúc đẩy xã hội Trung Hoa thời bấy giờ phát triển mà còn có ảnh hưởng sâu xa trong hiện đại.\n" +
                "\n" +
                "Cuốn sách ''Mặc Tử mưu lược tung hoành\" tập trung luận bàn về tư tưởng và sách lược của Mặc Tử trên 5 phương diện: chính trị, quân sự, kinh tế, làm người và biện luận. Mỗi nội dung đều có trích dẫn  sách Mặc Tử, với những lập luận chặt chẽ và các dẫn chứng minh họa thực tế sinh động.\n" +
                "\n" +
                "Lão Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "Lão Tử là một nhân vật chính yếu trong triết học Trung Quốc, sự tồn tại của ông trong lịch sử hiện vẫn đang còn được tranh cãi. Trong khoảng năm 580 đến 571 TCN, gia tộc nước Tống có thêm một thành viên mới, đó chính là Lão Tử, người mà sau này có ảnh hưởng rất lớn đến lịch sử Trung Quốc, đặc biệt là đối với việc hình thành những tư tưởng văn hóa.\n" +
                "\n" +
                "Lão Tử đã từng giữ chức sử quan suốt 30 năm trông coi tàng thất như thư viện quốc gia. Khi  nhận thấy chính sự nhà Chu đang đi vào thời kỳ tan rã, Lão Tử quyết định ra đi. Ông đi về phía Tây trên lưng một con trâu qua nước Tần và từ đó biến mất vào sa mạc rộng lớn.\n" +
                "\n" +
                "Sách Lão Tử mưu lược tung hoành chủ yếu đi sâu vào tác phẩm “Đạo Đức Kinh” để trình bày tư tưởng của Lão Tử về các phương diện trị quốc, dụng binh, mưu sự, nhân sinh, nêu bật tầm ảnh hưởng của ông đối với đương thời và cả hậu thế,nhà sách onligóp thêm một góc nhìn cho việc nghiên cứu tư tưởng Lão Tử.\n" +
                "\n" +
                "Trang Tử Mưu Lược Tung Hoành\n" +
                "\n" +
                "Nam Hoa kinh của Trang Tử cùng với Đạo Đức kinh của Lão Tử tập hợp tất cả tinh hoa của Đạo giáo, là những cuốn sách triết học nổi tiếng được lưu truyền qua hàng ngàn năm. Trong Nam Hoa kinh, tư tưởng triết học của Trang Tử được biểu hiện một cách giản dị nhưng cũng rất huyền hoặc, thâm trầm, phản ánh mọi sắc thái vừa cao sâu vừa vi tế của nhân sinh, vũ trụ.\n" +
                "\n" +
                "Sách Trang Tử mưu lược tung hoành - dựa vào nội dung, tư tưởng tổng thể của Nam Hoa kinh để quy nạp và trình bày các cách xử thế cũng như cách tu dưỡng tinh thần để có thể an nhiên, bình thản trước mọi khó khăn trong cuộc sống. Theo Trang Tử, con người muốn có được cuộc sống lý tưởng phải nắm được quy luật vận động và phát triển của tự nhiên, hành động theo quy luật, giữ tâm hư tĩnh điềm đạm, không thiên lệch, không danh lợi, tự do tự tại, phù hợp với đạo.");
        sach6.setGioiThieu("“Hậu duệ mặt trời” – Bộ phim là cả thanh xuân của chúng ta. Ai trong chúng ta cũng từng đem lòng yêu một chàng trai tên Soong Jong Ki và thầm nhớ một cô gái mùa thu Song Hye Kyo.\n" +
                "\n" +
                "Bạn có phải là một trong số những người may mắn ấy để sở hữu 2000 bộ photo essay được phát hành độc quyền tại Việt Nam.\n" +
                "\n" +
                "324 trang tranh màu và nội dung hấp dẫn\n" +
                "Những lời thoại kinh điển nhất, cảnh quay hot nhất của bộ phim được tái hiện.\n" +
                "Tặng kèm bộ 6 poscard có chữ kí của cặp đôi Song Song và đôi diễn phụ.\n" +
                "Tặng kèm 1 poster màu 2 mặt siêu lung linh.\n" +
                "Còn chần chừ gì mà không sở hữu ngay siêu phẩm “Hậu duệ mặt trời” HOT tại mùa hè này?\n" +
                "\n" +
                "Mintbooks trân trọng thông báo sách ảnh Hậu duệ mặt trời được phát hành như một món quà tri ân dành cho những độc giả mến mộ cặp đôi Song Joong Ki Song Hye Kyo.\n" +
                "\n" +
                "Photo essay “ Hậu duệ mặt trời” tái hiện một cách sinh động nhất chuyện tình giữa Đại úy Yoo Shi Jin và nữ bác sĩ thuộc tổ chức Bác sĩ không biên giới Kang Mo Yeon cũng như mối duyên nợ đầy đầy xót xa nơi sa trường của Thượng Sĩ Seo Dae Young với Trung úy Yoon Myung Joo bằng những khung hình đẹp nhất trong bộ phim cùng tên.\n" +
                "\n" +
                "Giống như những thước phim quay chậm, những hình ảnh được sắp xếp theo mạch phim, đính kèm những đoạn hội thoại hay những câu nói từng khiến trái tim bao cô gái, chàng trai thổn thức. Và trên hết, chúng từng tạo thành trao lưu mang tên\n" +
                "\n" +
                "“Song Joong Ki” gây bão khắp các nước châu Á năm 2016. Những lát cắt, những hình ảnh hậu tường lần đầu tiên được ra mắt độc giả đảm bảo sẽ khiến bạn một lần nữa cảm thấy “sục sôi”, “cười tủm tỉm” vì cặp đôi “phim giả tình thật” đình đám nhất xứ sở kim chi...\n" +
                "\n" +
                "“Nhân thể đang nói về chuyện lấy máu, bác sỹ Kang nhóm máu gì vậy?”\n" +
                "\n" +
                "Nhóm máu người trong mộng của anh?\n" +
                "\n" +
                "Thua luôn. Thế rốt cục nhóm máu của em là gì?Nhóm chịu án chung thân trong nhà tù mang tên anh?”\n" +
                "\n" +
                "Cuốn sách còn là minh chứng tình yêu đẹp đẽ nhất của cặp đôi Song Song, từng thước phim được ghi lại qua từng trang sách còn là những hoài niệm những giây phút chờ đợi bộ phim phát sóng. Cặp đôi Song Joong Ki và Song Hye Kyo từng là giấc mộng thanh xuân của bao người, nay đã nắm tay đến cuối cuộc đời\n" +
                "\n" +
                "“Điều anh muốn không phải là cùng em hẹn hò hay đơn thuần yêu đương, mà chính là kết hôn với em” Song Joong Ki.\n" +
                "\n" +
                "Sở hữu ngay cuốn sách này để ôm cả những mơ mộng của tuổi thanh xuân vào lòng, bạn nhé!");
        sach7.setGioiThieu("Thông tin chung\n" +
                "Nằm trong số những tác phẩm của tác giả nổi tiếng Gomi Taro, truyện được xuất bản lần đầu năm 1989. Truyện có nét vẽ đơn giản nhưng mô tả được nhiều bối cảnh, tình huống khiến trẻ thích thú tìm hiểu.\n" +
                "Tóm tắt\n" +
                "Mỗi bước nhảy của bạn Châu Chấu sẽ dẫn các bạn nhỏ đi đâu nhỉ? Những nơi ấy có gì thú vị? Cùng bạn Châu Chấu đi dạo nào!\n" +
                "Đối tượng độc giả\n" +
                "Đọc cho bé: từ 2 tuổi trở lên\n" +
                "Bé tự đọc: từ 6 tuổi trở lên.");
        sach8.setGioiThieu("\n" +
                "■ Thông tin chung\n" +
                "\n" +
                "1 trong số ít những tác phẩm của Gomi Taro được thể hiện bằng nhiều màu sắc bắt mắt, xuất bản lần đầu năm 2014. Truyện có nhiều nhân vật, chi tiết, đồ vật,… được vẽ ngộ nghĩnh.\n" +
                "\n" +
                " \n" +
                "\n" +
                "■ Tóm tắt\n" +
                "\n" +
                "13 mẩu chuyện nhỏ nêu lên 13 tình huống thú vị khác nhau để các bé suy nghĩ và đưa ra ý kiến. Các bé sẽ lựa chọn thế nào nhỉ?  Chắc hẳn nhiều bé sẽ có câu trả lời đặc biệt lắm đây!\n" +
                "\n" +
                "■ Đối tượng độc giả\n" +
                "\n" +
                "Đọc cho bé: từ 3 tuổi trở lên\n" +
                "\n" +
                "Bé tự đọc: từ 6 tuổi trở lên");
        sach9.setGioiThieu("Magic Kaito - Treasured Edition (Tập 5)\n" +
                "\n" +
                "Không bao giờ để lỡ mục tiêu, cách ra tay đầy bí ẩn... Thiên tài hóa trang, có thể biến thành ngàn vạn con người. Đó chính là siêu đạo chíck Kid, kẻ luôn nằm trong tầm ngắm gắt gao của giới cảnh sát nhưng lại là thần tượng của công chúng và được xưng tụng như một “sứ giả chính nghĩa”! 8 năm trôi qua kể từ khi Kid biến mất không dấu vết, “chiếc bóng trắng” huyền thoại bỗng trở lại trên nền trời đen với những phi vụ mới đầy táo tợn.\n" +
                "\n" +
                "Siêu đạo chích 1412 đã tái xuất giang hồ. Vẫn vận trên người bộ đồ ảo thuật trắng, vẫn nụ cười nửa miệng đầy ngạo nghễ, nhưng lần “come back” này Kid sẽ xuất hiện trong một “hình thức” mới đầy sang chảnh, đảm bảo nóng bỏng tay!");

        sach.setKickThuoc("13 x 20.5 cm");
        sach1.setKickThuoc("19 x 27 cm");
        sach2.setKickThuoc("14.5 x 20.5 cm");
        sach3.setKickThuoc("14.5 x 20.5 cm");
        sach4.setKickThuoc("16 x 24 cm");
        sach5.setKickThuoc("13.5 x 21.5 cm");
        sach6.setKickThuoc("16 x 24 cm");
        sach7.setKickThuoc("20 x 21 cm");
        sach8.setKickThuoc("23 x 25 cm");
        sach9.setKickThuoc("11.3 x 17.6 cm");

        sach.setTenSach("Thanh Xuân Không Hối Tiếc");
        sach1.setTenSach("Dạy Con Học Nói");
        sach2.setTenSach("Tiếng Chim Hót Trong Bụi Mận Gai");
        sach3.setTenSach("Đắc Nhân Tâm");
        sach4.setTenSach("Hào Kiệt Lam Sơn");
        sach5.setTenSach("Chư Tử Mưu Lược Tung Hoành 1 (Bộ 5 Cuốn)");
        sach6.setTenSach("Hậu duệ mặt trời");
        sach7.setTenSach("Truyện Tranh Ehon - Châu Chấu Đi Dạo");
        sach8.setTenSach("Truyện Tranh Ehon - Nếu Là Cậu Thì Làm Thế Nào?");
        sach9.setTenSach("Magic Kaito - Treasured Edition (Tập 5)");

        sach.setCongTyPhatHanh("Saigon Books");
        sach1.setCongTyPhatHanh("Nhà sách Minh Thắng");
        sach2.setCongTyPhatHanh("CÔNG TY TNHH PHÁT TRIỂN NĂNG LỰC THÀNH ĐẠT");
        sach3.setCongTyPhatHanh("First News - Trí Việt");
        sach4.setCongTyPhatHanh("Văn Lang");
        sach5.setCongTyPhatHanh("Văn Lang");
        sach6.setCongTyPhatHanh("Mintbooks");
        sach7.setCongTyPhatHanh("Công ty TNHH More Production Việt Nam");
        sach8.setCongTyPhatHanh("Công ty TNHH More Production Việt Nam");
        sach9.setCongTyPhatHanh("Nhà Xuất Bản Kim Đồng");

        sach.setSoTrang(195);
        sach1.setSoTrang(240);
        sach2.setSoTrang(866);
        sach3.setSoTrang(320);
        sach4.setSoTrang(1774);
        sach5.setSoTrang(1522);
        sach6.setSoTrang(324);
        sach7.setSoTrang(26);
        sach8.setSoTrang(32);
        sach9.setSoTrang(100);

        sach.setNgayXuatBan(Date.valueOf(LocalDate.of(2017, 10, 1)));
        sach1.setNgayXuatBan(Date.valueOf(LocalDate.of(2017, 11, 1)));
        sach2.setNgayXuatBan(Date.valueOf(LocalDate.of(2011, 07, 1)));
        sach3.setNgayXuatBan(Date.valueOf(LocalDate.of(2016, 03, 1)));
        sach4.setNgayXuatBan(Date.valueOf(LocalDate.of(2003, 03, 1)));
        sach5.setNgayXuatBan(Date.valueOf(LocalDate.of(2016, 06, 1)));
        sach6.setNgayXuatBan(Date.valueOf(LocalDate.of(2018, 04, 1)));
        sach7.setNgayXuatBan(Date.valueOf(LocalDate.of(2018, 04, 1)));
        sach8.setNgayXuatBan(Date.valueOf(LocalDate.of(2018, 04, 1)));
        sach9.setNgayXuatBan(Date.valueOf(LocalDate.of(2018, 05, 1)));

        entityManager.persist(tacGia);
        entityManager.persist(sach);
        entityManager.persist(tacGia1);
        entityManager.persist(sach1);
        entityManager.persist(tacGia2);
        entityManager.persist(sach2);
        entityManager.persist(tacGia3);
        entityManager.persist(sach3);
        entityManager.persist(tacGia4);
        entityManager.persist(sach4);
        entityManager.persist(tacGia5);
        entityManager.persist(sach5);
        entityManager.persist(tacGia6);
        entityManager.persist(sach6);
        entityManager.persist(tacGia7);
        entityManager.persist(sach7);
        entityManager.persist(tacGia8);
        entityManager.persist(sach8);
        entityManager.persist(tacGia9);
        entityManager.persist(sach9);

        sach.addTacGia(tacGia);
        sach1.addTacGia(tacGia1);
        sach2.addTacGia(tacGia2);
        sach3.addTacGia(tacGia3);
        sach4.addTacGia(tacGia4);
        sach5.addTacGia(tacGia5);
        sach6.addTacGia(tacGia6);
        sach7.addTacGia(tacGia7);
        sach8.addTacGia(tacGia8);
        sach9.addTacGia(tacGia9);

        tacGia.addSach(sach);
        tacGia1.addSach(sach1);
        tacGia2.addSach(sach2);
        tacGia3.addSach(sach3);
        tacGia4.addSach(sach4);
        tacGia5.addSach(sach5);
        tacGia6.addSach(sach6);
        tacGia7.addSach(sach7);
        tacGia8.addSach(sach8);
        tacGia9.addSach(sach9);

        entityManager.flush();
    }

    @Test
    @Transactional
    public void testGetData() {
//         Test getDataSach ---------------------------
//        Sach sach = entityManager.find(Sach.class, 1L);
//        List<TacGia> lstAllTacGia = sach.getAuthors();
//        System.out.println(sach.toString());
//        lstAllTacGia.forEach(e -> System.out.println(e.getTenTacGia()));
//         Test getDataSach ---------------------------

        // Test getDataHoaDon
        HoaDon hoaDon = entityManager.find(HoaDon.class, 4L);
        DiaChi diaChiHoaDon = hoaDon.getDiaChi();

        logger.info("Hoa don info -> {}", hoaDon);
//        logger.info("Dia chi info -> {}", diaChiHoaDon);
    }


    @Autowired
    GioHangService gioHangService;

    @Test
    @Transactional
    public void TestGioHang() {
        // Lay sach trong csdl
        Sach sachA = entityManager.find(Sach.class, 22L);
        Sach sachB = entityManager.find(Sach.class, 24L);
        gioHangService
                .addSach(sachA, 10);
        gioHangService
                .addSach(sachB, 10);

        // Dua vao gio hang tinh tien
        gioHangService
                .getAllSach()
                .forEach((sach, soLuong) -> logger.info("Element of gioHang => {}",
                        gioHangService.getChiTiet(sach, soLuong)));
        logger
                .info("Thanh tien => {}", gioHangService.thanhTien());

        // Bot sach di
        gioHangService
                .subtractSach(sachA, 2);

        Long amountLeft = gioHangService.getAllSach().get(sachA);
        Assert.assertTrue(amountLeft == 8);

        double tienSachA = sachA.getGiaSachBan() * 8;
        double tienSachB = sachB.getGiaSachBan() * 10;

        double tongTienSach =
                (tienSachA * (100 - sachA.getTiLeKhuyenMai()) +
                tienSachB * (100 - sachA.getTiLeKhuyenMai())) / 100;
        Assert.assertTrue(tongTienSach == gioHangService.thanhTien());

        // Tinh tien
        gioHangService
                .getAllSach()
                .forEach((sach, soLuong) -> logger.info("Element of gioHang => {}",
                        gioHangService.getChiTiet(sach, soLuong)));
        logger
                .info("Thanh tien => {}", gioHangService.thanhTien());
    }

    @Test
    @Transactional
    public void performance() {
        EntityGraph<DiaChi> hoaDonEntityGraph = entityManager.createEntityGraph(DiaChi.class);
        Subgraph<List<HoaDon>> lstHoaDon = hoaDonEntityGraph.addSubgraph("lstHoaDon");

        List<DiaChi> test_test = entityManager.createNamedQuery("test_test", DiaChi.class)
                .setHint("javax.persistence.loadgraph", hoaDonEntityGraph)
                .getResultList();

        for (DiaChi d :
                test_test) {
            logger.info("Messesage from => {}", d.toString());
        }

    }

}
