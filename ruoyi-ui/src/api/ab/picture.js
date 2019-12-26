import request from '@/utils/request'

// 查询图片列表
export function listPicture(query) {
  return request({
    url: '/ab/picture/list',
    method: 'get',
    params: query
  })
}

// 查询图片详细
export function getPicture(id) {
  return request({
    url: '/ab/picture/' + id,
    method: 'get'
  })
}

// 新增图片
export function addPicture(data) {
  return request({
    url: '/ab/picture',
    method: 'post',
    data: data
  })
}
//审核
export function review(id) {
  return request({
    url: '/ab/picture/review?id='+id,
    method: 'post'
  })
}


// 修改图片
export function updatePicture(data) {
  return request({
    url: '/ab/picture',
    method: 'put',
    data: data
  })
}

// 删除图片
export function delPicture(id) {
  return request({
    url: '/ab/picture/' + id,
    method: 'delete'
  })
}

// 导出图片
export function exportPicture(query) {
  return request({
    url: '/ab/picture/export',
    method: 'get',
    params: query
  })
}

// 查询图集详细
export function getAtlasList() {

  let s = request({
    url: '/ab/atlas/all',
    method: 'get'
  })
  console.log(s);
  return s;
}
