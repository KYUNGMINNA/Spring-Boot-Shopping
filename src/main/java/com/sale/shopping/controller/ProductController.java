package com.sale.shopping.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sale.shopping.controller.api.ProductAPIController;
import com.sale.shopping.controller.api.ProductOrderAPIController;
import com.sale.shopping.model.dto.CommonDTO;
import com.sale.shopping.model.dto.ProductOrderResponseDTO;
import com.sale.shopping.model.entity.Product;
import com.sale.shopping.model.entity.ProductOrder;
import com.sale.shopping.repository.ProductRepository;
import com.sale.shopping.service.ProductOrderService;
import com.sale.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller

public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductAPIController productAPIController;
    @Autowired
    private ProductOrderAPIController productOrderAPIController;


    @GetMapping({"/", ""})
    public String home(Model model) {

        model.addAttribute("saladList", productAPIController.selectLimitProduct("샐러드"));
        model.addAttribute("convenienceList", productAPIController.selectLimitProduct("간편식"));
        model.addAttribute("lunchBoxList", productAPIController.selectLimitProduct("도시락"));

        //model.addAttribute("productList",productRepository.findTop4ByProductCategoryOrderByIdDesc("샐러드"));
        //단순한 방법
        return "home";
    }

    @GetMapping("/product/category")
    public String productCategory(Model model, @RequestParam String category, @PageableDefault(size = 16, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) throws JsonProcessingException {
        Page<Product> productpage = productService.selectAllProduct(category, pageable);

        int nowPage = productpage.getPageable().getPageNumber() + 1; // 현재페이지 : 0 에서 시작하기에 1을 더해준다.
        int firstlistpage = 1;
        int lastlistpage = 10;
        boolean listpagecheckflg = false;

        while (listpagecheckflg == false) {
            if (productpage.getTotalPages() == 0) {
                lastlistpage = 1;
                listpagecheckflg = true;
            }
            if (lastlistpage > productpage.getTotalPages()) {
                lastlistpage = productpage.getTotalPages();
            }
            if (nowPage >= firstlistpage && nowPage <= lastlistpage) {
                listpagecheckflg = true;
            } else {
                firstlistpage += 10;
                lastlistpage += 10;
            }
        }
        List<Object> page = new ArrayList<>();
        page.add(nowPage);
        page.add(productpage.getTotalPages());
        page.add(firstlistpage);
        page.add(lastlistpage);

        model.addAttribute("paging", page);
        model.addAttribute("productList", productpage);
        return "productCategory";

    }

    @GetMapping("/product/productselect")
    public String select(Model model, @RequestParam String category, @RequestParam String title) {
        CommonDTO dto = (CommonDTO) productAPIController.SelectOneProductAPI(category, title).getBody();
        model.addAttribute("oneProduct", dto);
        return "productSelect";
    }

    @GetMapping("/product/purchase")
    public String purchase(Model model, @RequestParam String category, @RequestParam String title, @RequestParam String count) {
        CommonDTO dto = (CommonDTO) productAPIController.SelectOneProductAPI(category, title).getBody();
        model.addAttribute("oneProduct", dto);
        return "productBuy";
    }

    @PostMapping("/product/productorder")
    public String orderNumber(Model model, @RequestParam Map<Object, Object> data) {
        CommonDTO productOrderCommonDTO = (CommonDTO) productOrderAPIController.selectProductOrderNumber(data.get("productOrderNumber").toString()).getBody();
        model.addAttribute("ordernumber", productOrderCommonDTO);
        return "productOrder";

    }
    @GetMapping("/product/mypage")
    public String mypage(){
        return "productMypage";
    }


}
