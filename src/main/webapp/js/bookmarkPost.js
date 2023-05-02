function getQueryStringObject() {
  var queryString = window.location.search.substring(1).split('&');
  if (queryString == "") return {};
  var queryStringObject = {};
  for (var i = 0; i < queryString.length; ++i) {
    var p = queryString[i].split('=', 2);
    if (p.length == 1)
      queryStringObject[p[0]] = "";
    else
      queryStringObject[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
  }
  return queryStringObject;
}
const onAddButtonClicked = () => {
  const name = document.querySelector('.bookmark-post-name-input');
  const order = document.querySelector('.bookmark-post-order-input');

  if(name.value === ''){
    alert("북마크 그룹 이름을 입력해주세요");
    return;
  }
  if(order.value === ''){
    alert("북마크 그룹 순서를 입력해주세요.");
    return;
  }
  if(order.value < 1){
    alert("북마크 그룹 순서는 자연수로 입력해주세요");
    return;
  }


  fetch(`/bookmark-group`,{
    method : "POST",
    headers : {
      'Content-Type': 'application/json',
    },
    body : JSON.stringify({
      name : name.value, orders : order.value
    })
  }).then(() => {
    alert("북마크 그룹이 추가되었습니다.")
    window.location.href = `/view/bookmarkGroupList.jsp`;
  }).catch(err => console.error(err));

}

function drawAddModePage(){
  let wrapper = document.querySelector('.bookmark-group-post-action-wrapper');
  let addBtn = document.createElement('button');
  addBtn.innerHTML = '추가';
  addBtn.addEventListener('click', onAddButtonClicked);
  wrapper.append(addBtn);

}

const onEditButtonClicked = (id) => {
  const name = document.querySelector('.bookmark-post-name-input');
  const order = document.querySelector('.bookmark-post-order-input');

  if(name.value === ''){
    alert("북마크 그룹 이름을 입력해주세요");
    return;
  }
  if(order.value === ''){
    alert("북마크 그룹 순서를 입력해주세요.");
    return;
  }
  if(order.value < 1){
    alert("북마크 그룹 순서는 자연수로 입력해주세요");
    return;
  }


  fetch(`/bookmark-group`,{
    method : "PUT",
    headers : {
      'Content-Type': 'application/json',
    },
    body : JSON.stringify({
      id : id,
      name : name.value, orders : order.value
    })
  }).then(() => {
    alert("북마크 그룹이 수정되었습니다.")
    window.location.href = `/view/bookmarkGroupList.jsp`;
  }).catch(err => console.error(err));
}
function drawEditModePage(id,prevName, prevOrder){
  let wrapper = document.querySelector('.bookmark-group-post-action-wrapper');
  let backAnchor = document.createElement('a');
  backAnchor.innerText = `돌아가기`;
  backAnchor.href = `/view/bookmarkGroupList.jsp`;

  let divider = document.createElement('span');
  divider.innerText = `ㅣ`;


  let addBtn = document.createElement('button');
  addBtn.innerHTML = '수정';
  addBtn.addEventListener('click', () =>onEditButtonClicked(id));

  wrapper.append(backAnchor);
  wrapper.append(divider);
  wrapper.append(addBtn);

  const name = document.querySelector('.bookmark-post-name-input');
  name.value = prevName;
  const order = document.querySelector('.bookmark-post-order-input');
  order.value = prevOrder
}



function init(){
  let queryString = getQueryStringObject();

  if(queryString.mode==="add"){
    drawAddModePage();
  }else if(queryString.mode === `edit`){
    drawEditModePage(queryString.id,queryString.name, queryString.order)
  }else{
    alert("너 뭐냐..")
  }
}
init();