const serviceKey =
	  "hFhQlPE6A5Tzme9oIWJlhgxbn3lEgj3R0hvcCM62zK4Kotk2BG9B1TRphLQfQWSKUIldSrVkCd%2FWxr0BWyqoJw%3D%3D";

//index page 로딩 후 전국의 시도 설정.
let areaUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  serviceKey +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

fetch(areaUrl, { method: "GET" })
  .then((response) => response.json())
  .then((data) => makeOption(data));

function btnClick(){
	let sidoNo = document.querySelector("#search-area").value;
	let gugunNo = document.getElementById("search-gugun-id").value;
	let contentTypeId = document.querySelector("#search-content-id").value;

	if (sidoNo==0 || gugunNo==0) {
	  alert("Select Options !!!");
	      return;
	  } else{
		  fetch("/attraction/search", { 
				method: "GET",
				headers:{
					"Content-Type" : "application/json",
					"sidoNo" : sidoNo,
					"gugunNo" : gugunNo,
					"contentTypeId" : contentTypeId,
				}
			})
			.then((response) => response.json())
		    .then((data) => makeList(data));
	  }
}
  
var bounds;
var positions; // marker 배열.
function makeList(response) {
	if(response.length === 0){ // 관광지 유형에 데이터가 없으면 alert 출력 후 return
		alert("Select Other Options !!!");
		return;
	} else{
		document.querySelector("#search-result").setAttribute("style", "display: ;");
		
	    let tripList = `<div class="row gy-4 posts-list">`;
	    positions = [];
	    response.forEach((area) => {
	        if (!area.firstImage) { // 기본 이미지
	          tripList += '<div class="col-xl-4 col-md-6">'
	          tripList += '<article> <div class="post-img"> <img src="./assets/img/noimg.jpg" alt="" class="img-fluid"/> </div>'
	          tripList += '<h2 class="title text-dark">'+area.title+' </h2>';
	          tripList += '<div class="d-flex align-items-center"> <div class="post-meta"> <p class="post-author-list">'+area.addr1+' '
	          if(area.addr2)tripList+='- '+area.addr2;
	          tripList += '</p><p class="post-author-list">'+area.zipcode+'</p></div> </div> </article> </div>';
	        } else { // db에 저장된 이미지
	          tripList += '<div class="col-xl-4 col-md-6">'
	          tripList += '<article> <div class="post-img"> <img src='+area.firstImage+' alt="" class="img-fluid" /> </div>'
	          tripList += '<h2 class="title text-dark">'+area.title+'</h2>';
	          tripList += '<div class="d-flex align-items-center"> <div class="post-meta"> <p class="post-author-list">'+area.addr1+' '
	          if(area.addr2)tripList+='- '+area.addr2;
	          tripList += '</p><p class="post-author-list">'+area.zipcode+'</p></div> </div> </article> </div>';
	        }

	        let markerInfo = {
	            title: area.title,
	            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
	        };
	        
	        positions.push(markerInfo);
	    });
	    
	    tripList += '</div>';
	    document.querySelector("#blog>.container").innerHTML = tripList;
	  
	    if(map) map = new kakao.maps.Map(mapContainer, mapOption); // 이전에 출력된 마커 없애기 위해 (마커 초기화)
	    displayMarker();
	    setBounds();
	}
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
      center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
      level: 5, // 지도의 확대 레벨
  };

//지도를 표시할 div와  지도 옵션으로  지도를 생성
var map = new kakao.maps.Map(mapContainer, mapOption);

function displayMarker() {
  // 마커 이미지의 이미지 주소
  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
  
  bounds = new kakao.maps.LatLngBounds();
  
  for (var i = 0; i < positions.length; i++) {
      // 마커 이미지의 이미지 크기
      var imageSize = new kakao.maps.Size(24, 35);

      // 마커 이미지를 생성
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시
          image: markerImage, // 마커 이미지
          position : positions[i].latlng,
      });
      
      marker.setMap(map);
      bounds.extend(positions[i].latlng);
  }
}

/* LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정 */
function setBounds() {
    map.setBounds(bounds);
}
