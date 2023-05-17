<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<main id="main">
      <!-- 메인 컨텐츠 시작 -->
      <div class="container">
          <!-- 관광지 조회 섹션 -->
          <section id="search" class="search">
              <!-- 관광지 조회 타이틀 -->
              <div class="row d-flex justify-content-center">
                  <div class="col-lg-6 text-center">
                      <h2 class="fw-bold">관광지 조회</h2>
                  </div>
              </div>
              <!-- 타이틀 종료 -->

              <div class="row d-flex justify-content-center ">
                  <div class="col-lg-10 text-center">
                      <form id="form-arrtaction"class="d-flex my-3" onsubmit="return false;" role="search">
                          <select id="search-area" class="form-select me-2" >
                              <!-- <option value="0" selected>지역</option> -->
								<c:forEach var="item" items="${sidoList}">    
					                <option value="${item.sidoNo}">${item.sidoName}</option>            
								</c:forEach>
                          </select>
                          
                          <select id="search-gugun-id" class="form-select me-2">
                          	<option value="0" selected >시/군/구</option>
                          </select>
                          
                          <select id="search-content-id" class="form-select me-2">
                              <option value="0" selected>관광지 유형</option>
                              <option value="12">관광지</option>
                              <option value="14">문화시설</option>
                              <option value="15">축제공연행사</option>
                              <option value="25">여행코스</option>
                              <option value="28">레포츠</option>
                              <option value="32">숙박</option>
                              <option value="38">쇼핑</option>
                              <option value="39">음식점</option>
                          </select>                              
                          <button id="btn-search" class="btn btn-outline-success" type="submit" onclick="btnClick()">검색</button>
                      </form>
                  </div>
              </div>
              <!-- kakao map start -->
              <div id="map" class="mt-3" style="width: 100%; height: 400px">
               </div>
              <!-- kakao map end -->

              <!-- 검색 결과 조회 -->
              <div class="row" id="search-result">
                  <section id="blog" class="blog">
                      <div class="container" data-aos="fade-up">
                      </div>
                  </section>
              </div>
              <!-- End 검색 결과 조회  -->
          </section>
          <!-- End 관광지 조회 섹션 -->
      </div>
      <!-- 메인 컨텐츠 -->
  </main>
  <!-- End #main -->
  
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c5aea05dba04c8446448c63fff995b63"></script>
<script src="/assets/js/map.js"></script> 

<script>
	function makeOption(response) {
		
        let sel = document.getElementById("search-gugun-id");
		sel.innerHTML='<option value="0" selected>시/군/구</option>';
		
		response.forEach((area) => {
            var opt = document.createElement("option");
            opt.setAttribute("value", area.gugunNo);
            opt.appendChild(document.createTextNode(area.gugunName));
            sel.appendChild(opt);
        });
    }
	
	function changeGugun(e) {
		let sidoCode = document.querySelector("#search-area").value;
		console.log(sidoCode)
		fetch("/attraction/getGugun/"+sidoCode, { method: "GET" })
			.then((response)=>response.json())
			.then((data)=>makeOption(data));
	}
	
	window.onload = () => {
		const searchArea = document.querySelector("#search-area");
		searchArea.addEventListener("change", function(e){
			changeGugun(e);
		})
	}	
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>