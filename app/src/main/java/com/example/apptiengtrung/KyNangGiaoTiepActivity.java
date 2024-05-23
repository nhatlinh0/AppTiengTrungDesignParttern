package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class KyNangGiaoTiepActivity extends AppCompatActivity {
    public static ArrayList<KyNangGiaoTiep> list;
    public String[] listChaoHoi = new String[] {"Xin chào", "Chào buổi sáng", "Chào buổi trưa", "Chào buổi tối", "Chúc ngủ ngon",
    "Bạn có khoẻ không?", "Cảm ơn, tôi khoẻ","Tốt","OK","Tương đối"};
    public String[] listHoiThoai = new String[] {"Có","Không","Bạn có hiểu không","Tôi không hiểu","Tôi hiểu","Cảm ơn",
    "Làm ơn...","Tôi xin lỗi","Xin hãy nhắc lại 1 lần","Bạn có thể nhắc lại được không"};
    public String[] listSoDem = new String[] {"Số không","Một","Hai","Ba","Bốn","Năm","Sáu","Bảy","Tám","Chín","Mười","Mười một",
    "Mười hai","Mười ba","Mười bốn","Mười lăm","Mười sáu","Mười bảy","Mười tám","Mười chín","Hai mươi"};
    public String[] listDiaDiem = new String[] {"cửa hàng","trường học","công viên","phòng làm việc","bưu điện","nhà ăn","viện bảo tàng",
    "bệnh viện","nhà sách"};
    public String[] listNgayThang = new String[] {"Tháng","Mùa","Năm","Thập kỉ","Thế kỉ","Ngàn năm","Vĩnh hằng","Sáng sớm",
    "Buổi sáng","Buổi trưa","Buổi chiều","Buổi tối"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kynanggiaotiep_layout);

        list = new ArrayList<>();
        list.add(new KyNangGiaoTiep(1,"Chào hỏi & giới thiệu", R.drawable.ic_handshake,listChaoHoi));
        list.add(new KyNangGiaoTiep(2,"Hội thoại hằng ngày", R.drawable.ic_message,listHoiThoai));
        list.add(new KyNangGiaoTiep(3,"Số đếm & thứ tự", R.drawable.ic_number,listSoDem));
        list.add(new KyNangGiaoTiep(4,"Thời gian & ngày tháng", R.drawable.ic_time,listNgayThang));
        list.add(new KyNangGiaoTiep(5,"Chỉ dẫn & địa điểm", R.drawable.ic_map,listDiaDiem));
        list.add(new KyNangGiaoTiep(6,"Giao thông & phương tiện", R.drawable.ic_car,listChaoHoi));
        list.add(new KyNangGiaoTiep(7,"Nơi cư trú & ăn nghỉ", R.drawable.ic_bed,listChaoHoi));
        list.add(new KyNangGiaoTiep(8,"Thực phẩm & nhà hàng", R.drawable.ic_restaurant,listChaoHoi));
        list.add(new KyNangGiaoTiep(9,"Mua sắm & giá cả", R.drawable.ic_shopping,listChaoHoi));
        list.add(new KyNangGiaoTiep(10,"Các quốc gia", R.drawable.ic_earth,listChaoHoi));
//        list.add(new KyNangGiaoTiep(11,"Gia đình", R.drawable.ic_family,listChaoHoi));
//        list.add(new KyNangGiaoTiep(12,"Hẹn hò", R.drawable.ic_heart,listChaoHoi));
//        list.add(new KyNangGiaoTiep(13,"Tình huống khẩn cấp", R.drawable.ic_plus,listChaoHoi));
//        list.add(new KyNangGiaoTiep(14,"Bị ốm", R.drawable.ic_sick,listChaoHoi));
//        list.add(new KyNangGiaoTiep(15,"Tính huống đặc biệt", R.drawable.ic_birthday,listChaoHoi));
//        list.add(new KyNangGiaoTiep(10,"Bộ phận cơ thể", R.drawable.ic_body,listChaoHoi));

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        ListView lvKyNangGiaoTiep = findViewById(R.id.lvKyNangGiaoTiep);
        KyNangGiaoTiepAdapter adapter = new KyNangGiaoTiepAdapter(this, list);
        lvKyNangGiaoTiep.setAdapter(adapter);
    }
}
