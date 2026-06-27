package com.example.electricitycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerProvince;
    private Spinner spinnerCity;
    private EditText editUsage;
    private Button btnCalculate;
    private TextView tvResult;
    private TextView tvDetail;
    private TextView tvPrice1, tvPrice2, tvPrice3;
    private TableRow rowTier1, rowTier2, rowTier3;

    private String[] provinces = {
        "北京市", "天津市", "河北省", "山西省", "内蒙古自治区",
        "辽宁省", "吉林省", "黑龙江省", "上海市", "江苏省",
        "浙江省", "安徽省", "福建省", "江西省", "山东省",
        "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区",
        "海南省", "重庆市", "四川省", "贵州省", "云南省",
        "西藏自治区", "陕西省", "甘肃省", "青海省", "宁夏回族自治区",
        "新疆维吾尔自治区"
    };

    private Map<String, List<String>> provinceCityMap = new HashMap<>();
    private Map<String, double[]> cityPriceMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
        setupSpinners();
        setupButton();
    }

    private void initViews() {
        spinnerProvince = findViewById(R.id.spinnerProvince);
        spinnerCity = findViewById(R.id.spinnerCity);
        editUsage = findViewById(R.id.editUsage);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        tvDetail = findViewById(R.id.tvDetail);
        tvPrice1 = findViewById(R.id.tvPrice1);
        tvPrice2 = findViewById(R.id.tvPrice2);
        tvPrice3 = findViewById(R.id.tvPrice3);
        rowTier1 = findViewById(R.id.rowTier1);
        rowTier2 = findViewById(R.id.rowTier2);
        rowTier3 = findViewById(R.id.rowTier3);
    }

    private void initData() {
        provinceCityMap.put("北京市", Arrays.asList("北京市"));
        provinceCityMap.put("天津市", Arrays.asList("天津市"));
        provinceCityMap.put("上海市", Arrays.asList("上海市"));
        provinceCityMap.put("重庆市", Arrays.asList("重庆市"));

        provinceCityMap.put("河北省", Arrays.asList("石家庄市", "唐山市", "秦皇岛市", "邯郸市", "邢台市", "保定市", "张家口市", "承德市", "沧州市", "廊坊市", "衡水市"));
        provinceCityMap.put("山西省", Arrays.asList("太原市", "大同市", "阳泉市", "长治市", "晋城市", "朔州市", "晋中市", "运城市", "忻州市", "临汾市", "吕梁市"));
        provinceCityMap.put("内蒙古自治区", Arrays.asList("呼和浩特市", "包头市", "乌海市", "赤峰市", "通辽市", "鄂尔多斯市", "呼伦贝尔市", "巴彦淖尔市", "乌兰察布市", "兴安盟", "锡林郭勒盟", "阿拉善盟"));
        provinceCityMap.put("辽宁省", Arrays.asList("沈阳市", "大连市", "鞍山市", "抚顺市", "本溪市", "丹东市", "锦州市", "营口市", "阜新市", "辽阳市", "盘锦市", "铁岭市", "朝阳市", "葫芦岛市"));
        provinceCityMap.put("吉林省", Arrays.asList("长春市", "吉林市", "四平市", "辽源市", "通化市", "白山市", "松原市", "白城市", "延边朝鲜族自治州"));
        provinceCityMap.put("黑龙江省", Arrays.asList("哈尔滨市", "齐齐哈尔市", "鸡西市", "鹤岗市", "双鸭山市", "大庆市", "伊春市", "佳木斯市", "七台河市", "牡丹江市", "黑河市", "绥化市", "大兴安岭地区"));
        provinceCityMap.put("江苏省", Arrays.asList("南京市", "无锡市", "徐州市", "常州市", "苏州市", "南通市", "连云港市", "淮安市", "盐城市", "扬州市", "镇江市", "泰州市", "宿迁市"));
        provinceCityMap.put("浙江省", Arrays.asList("杭州市", "宁波市", "温州市", "嘉兴市", "湖州市", "绍兴市", "金华市", "衢州市", "舟山市", "台州市", "丽水市"));
        provinceCityMap.put("安徽省", Arrays.asList("合肥市", "芜湖市", "蚌埠市", "淮南市", "马鞍山市", "淮北市", "铜陵市", "安庆市", "黄山市", "滁州市", "阜阳市", "宿州市", "六安市", "亳州市", "池州市", "宣城市"));
        provinceCityMap.put("福建省", Arrays.asList("福州市", "厦门市", "莆田市", "三明市", "泉州市", "漳州市", "南平市", "龙岩市", "宁德市"));
        provinceCityMap.put("江西省", Arrays.asList("南昌市", "景德镇市", "萍乡市", "九江市", "新余市", "鹰潭市", "赣州市", "吉安市", "宜春市", "抚州市", "上饶市"));
        provinceCityMap.put("山东省", Arrays.asList("济南市", "青岛市", "淄博市", "枣庄市", "东营市", "烟台市", "潍坊市", "济宁市", "泰安市", "威海市", "日照市", "临沂市", "德州市", "聊城市", "滨州市", "菏泽市"));
        provinceCityMap.put("河南省", Arrays.asList("郑州市", "开封市", "洛阳市", "平顶山市", "安阳市", "鹤壁市", "新乡市", "焦作市", "濮阳市", "许昌市", "漯河市", "三门峡市", "南阳市", "商丘市", "信阳市", "周口市", "驻马店市"));
        provinceCityMap.put("湖北省", Arrays.asList("武汉市", "黄石市", "十堰市", "宜昌市", "襄阳市", "鄂州市", "荆门市", "孝感市", "荆州市", "黄冈市", "咸宁市", "随州市", "恩施土家族苗族自治州"));
        provinceCityMap.put("湖南省", Arrays.asList("长沙市", "株洲市", "湘潭市", "衡阳市", "邵阳市", "岳阳市", "常德市", "张家界市", "益阳市", "郴州市", "永州市", "怀化市", "娄底市", "湘西土家族苗族自治州"));
        provinceCityMap.put("广东省", Arrays.asList("广州市", "韶关市", "深圳市", "珠海市", "汕头市", "佛山市", "江门市", "湛江市", "茂名市", "肇庆市", "惠州市", "梅州市", "汕尾市", "河源市", "阳江市", "清远市", "东莞市", "中山市", "潮州市", "揭阳市", "云浮市"));
        provinceCityMap.put("广西壮族自治区", Arrays.asList("南宁市", "柳州市", "桂林市", "梧州市", "北海市", "防城港市", "钦州市", "贵港市", "玉林市", "百色市", "贺州市", "河池市", "来宾市", "崇左市"));
        provinceCityMap.put("海南省", Arrays.asList("海口市", "三亚市", "三沙市", "儋州市"));
        provinceCityMap.put("四川省", Arrays.asList("成都市", "自贡市", "攀枝花市", "泸州市", "德阳市", "绵阳市", "广元市", "遂宁市", "内江市", "乐山市", "南充市", "眉山市", "宜宾市", "广安市", "达州市", "雅安市", "巴中市", "资阳市", "阿坝藏族羌族自治州", "甘孜藏族自治州", "凉山彝族自治州"));
        provinceCityMap.put("贵州省", Arrays.asList("贵阳市", "六盘水市", "遵义市", "安顺市", "毕节市", "铜仁市", "黔西南布依族苗族自治州", "黔东南苗族侗族自治州", "黔南布依族苗族自治州"));
        provinceCityMap.put("云南省", Arrays.asList("昆明市", "曲靖市", "玉溪市", "保山市", "昭通市", "丽江市", "普洱市", "临沧市", "楚雄彝族自治州", "红河哈尼族彝族自治州", "文山壮族苗族自治州", "西双版纳傣族自治州", "大理白族自治州", "德宏傣族景颇族自治州", "怒江傈僳族自治州", "迪庆藏族自治州"));
        provinceCityMap.put("西藏自治区", Arrays.asList("拉萨市", "日喀则市", "昌都市", "林芝市", "山南市", "那曲市", "阿里地区"));
        provinceCityMap.put("陕西省", Arrays.asList("西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市", "延安市", "汉中市", "榆林市", "安康市", "商洛市"));
        provinceCityMap.put("甘肃省", Arrays.asList("兰州市", "嘉峪关市", "金昌市", "白银市", "天水市", "武威市", "张掖市", "平凉市", "酒泉市", "庆阳市", "定西市", "陇南市", "临夏回族自治州", "甘南藏族自治州"));
        provinceCityMap.put("青海省", Arrays.asList("西宁市", "海东市", "海北藏族自治州", "黄南藏族自治州", "海南藏族自治州", "果洛藏族自治州", "玉树藏族自治州", "海西蒙古族藏族自治州"));
        provinceCityMap.put("宁夏回族自治区", Arrays.asList("银川市", "石嘴山市", "吴忠市", "固原市", "中卫市"));
        provinceCityMap.put("新疆维吾尔自治区", Arrays.asList("乌鲁木齐市", "克拉玛依市", "吐鲁番市", "哈密市", "昌吉回族自治州", "博尔塔拉蒙古自治州", "巴音郭楞蒙古自治州", "阿克苏地区", "克孜勒苏柯尔克孜自治州", "喀什地区", "和田地区", "伊犁哈萨克自治州", "塔城地区", "阿勒泰地区"));

        // 电价数据（元/度）
        cityPriceMap.put("北京市", new double[]{0.4883, 0.5383, 0.7883});
        cityPriceMap.put("天津市", new double[]{0.4900, 0.5400, 0.7900});
        for (String c : provinceCityMap.getOrDefault("河北省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5200, 0.5700, 0.8200}); }
        for (String c : provinceCityMap.getOrDefault("山西省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.4770, 0.5270, 0.7770}); }
        for (String c : provinceCityMap.getOrDefault("内蒙古自治区", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.4450, 0.4950, 0.7450}); }
        for (String c : provinceCityMap.getOrDefault("辽宁省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5000, 0.5500, 0.8000}); }
        for (String c : provinceCityMap.getOrDefault("吉林省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5250, 0.5750, 0.8250}); }
        for (String c : provinceCityMap.getOrDefault("黑龙江省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5100, 0.5600, 0.8100}); }
        cityPriceMap.put("上海市", new double[]{0.6170, 0.6670, 0.9170});
        for (String c : provinceCityMap.getOrDefault("江苏省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5283, 0.5783, 0.8283}); }
        for (String c : provinceCityMap.getOrDefault("浙江省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5380, 0.5880, 0.8380}); }
        for (String c : provinceCityMap.getOrDefault("安徽省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5653, 0.6153, 0.8653}); }
        for (String c : provinceCityMap.getOrDefault("福建省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.4983, 0.5483, 0.7983}); }
        for (String c : provinceCityMap.getOrDefault("江西省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.6000, 0.6500, 0.9000}); }
        for (String c : provinceCityMap.getOrDefault("山东省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5469, 0.5969, 0.8469}); }
        for (String c : provinceCityMap.getOrDefault("河南省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5600, 0.6100, 0.8600}); }
        for (String c : provinceCityMap.getOrDefault("湖北省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5730, 0.6230, 0.8730}); }
        for (String c : provinceCityMap.getOrDefault("湖南省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5880, 0.6380, 0.8880}); }
        for (String c : provinceCityMap.getOrDefault("广东省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5989, 0.6489, 0.8989}); }
        for (String c : provinceCityMap.getOrDefault("广西壮族自治区", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5283, 0.5783, 0.8283}); }
        for (String c : provinceCityMap.getOrDefault("海南省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.6083, 0.6583, 0.9083}); }
        cityPriceMap.put("重庆市", new double[]{0.5200, 0.5710, 0.8210});
        for (String c : provinceCityMap.getOrDefault("四川省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5224, 0.5724, 0.8224}); }
        for (String c : provinceCityMap.getOrDefault("贵州省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.4556, 0.5056, 0.7556}); }
        for (String c : provinceCityMap.getOrDefault("云南省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5000, 0.5500, 0.8000}); }
        for (String c : provinceCityMap.getOrDefault("西藏自治区", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.4956, 0.5456, 0.7956}); }
        for (String c : provinceCityMap.getOrDefault("陕西省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.4983, 0.5483, 0.7983}); }
        for (String c : provinceCityMap.getOrDefault("甘肃省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5116, 0.5616, 0.8116}); }
        for (String c : provinceCityMap.getOrDefault("青海省", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.3771, 0.4271, 0.6771}); }
        for (String c : provinceCityMap.getOrDefault("宁夏回族自治区", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.4486, 0.4986, 0.7486}); }
        for (String c : provinceCityMap.getOrDefault("新疆维吾尔自治区", new ArrayList<>())) { cityPriceMap.put(c, new double[]{0.5000, 0.5500, 0.8000}); }
    }

    private void setupSpinners() {
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, provinces);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(provinceAdapter);

        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateCitySpinner(provinces[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void updateCitySpinner(String province) {
        List<String> cities = provinceCityMap.get(province);
        if (cities == null) cities = new ArrayList<>();

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);

        if (!cities.isEmpty()) {
            updatePriceDisplay(cities.get(0));
        }

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePriceDisplay(cities.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void updatePriceDisplay(String city) {
        double[] prices = cityPriceMap.get(city);
        if (prices != null && prices.length >= 3) {
            tvPrice1.setText(String.format("%.4f", prices[0]));
            tvPrice2.setText(String.format("%.4f", prices[1]));
            tvPrice3.setText(String.format("%.4f", prices[2]));
            rowTier1.setVisibility(View.VISIBLE);
            rowTier2.setVisibility(View.VISIBLE);
            rowTier3.setVisibility(View.VISIBLE);
        } else {
            tvPrice1.setText("-");
            tvPrice2.setText("-");
            tvPrice3.setText("-");
        }
    }

    private void setupButton() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateElectricity();
            }
        });
    }

    private void calculateElectricity() {
        String usageStr = editUsage.getText().toString().trim();

        if (usageStr.isEmpty()) {
            tvResult.setText("请输入用电量");
            tvDetail.setText("");
            return;
        }

        double usage;
        try {
            usage = Double.parseDouble(usageStr);
        } catch (NumberFormatException e) {
            tvResult.setText("输入格式错误");
            tvDetail.setText("");
            return;
        }

        if (usage <= 0) {
            tvResult.setText("用电量必须大于0");
            tvDetail.setText("");
            return;
        }

        String selectedCity = (String) spinnerCity.getSelectedItem();
        if (selectedCity == null || selectedCity.isEmpty()) {
            tvResult.setText("请先选择城市");
            return;
        }

        double[] prices = cityPriceMap.get(selectedCity);
        if (prices == null) {
            tvResult.setText("暂无该城市电价数据");
            return;
        }

        double tier1Limit = 180;
        double tier2Limit = 350;

        double totalCost = 0;
        double cost1 = 0, cost2 = 0, cost3 = 0;
        double usage1 = 0, usage2 = 0, usage3 = 0;
        StringBuilder detailBuilder = new StringBuilder();
        detailBuilder.append(selectedCity).append(" 阶梯计费明细：\n");

        if (usage <= tier1Limit) {
            cost1 = usage * prices[0];
            totalCost = cost1;
            usage1 = usage;
            detailBuilder.append(String.format("  第1档: %.1f度 x %.4f元 = %.2f元\n", usage, prices[0], cost1));
        } else if (usage <= tier2Limit) {
            cost1 = tier1Limit * prices[0];
            usage1 = tier1Limit;
            usage2 = usage - tier1Limit;
            cost2 = usage2 * prices[1];
            totalCost = cost1 + cost2;
            detailBuilder.append(String.format("  第1档: %.1f度 x %.4f元 = %.2f元\n", tier1Limit, prices[0], cost1));
            detailBuilder.append(String.format("  第2档: %.1f度 x %.4f元 = %.2f元\n", usage2, prices[1], cost2));
        } else {
            cost1 = tier1Limit * prices[0];
            usage1 = tier1Limit;
            usage2 = tier2Limit - tier1Limit;
            cost2 = usage2 * prices[1];
            usage3 = usage - tier2Limit;
            cost3 = usage3 * prices[2];
            totalCost = cost1 + cost2 + cost3;
            detailBuilder.append(String.format("  第1档: %.1f度 x %.4f元 = %.2f元\n", tier1Limit, prices[0], cost1));
            detailBuilder.append(String.format("  第2档: %.1f度 x %.4f元 = %.2f元\n", usage2, prices[1], cost2));
            detailBuilder.append(String.format("  第3档: %.1f度 x %.4f元 = %.2f元\n", usage3, prices[2], cost3));
        }

        detailBuilder.append(String.format("─────────────\n总计: %.2f元", totalCost));

        tvResult.setText(String.format("%.2f 元", totalCost));
        tvDetail.setText(detailBuilder.toString());
    }
}
