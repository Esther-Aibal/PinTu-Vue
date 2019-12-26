import request from '@/utils/request'

// 查询图集列表
export function listAtlas(query) {
  return request({
    url: '/ab/atlas/list',
    method: 'get',
    params: query
  })
}

// 查询图集详细
export function getAtlas(id) {
  return request({
    url: '/ab/atlas/' + id,
    method: 'get'
  })
}

// 查询图集详细
export function getMaps() {
  let s = request({
              url: '/ab/map/all',
              method: 'get'
  })
  console.log(s);
  return s;
}
//审核
export function review(id) {
  return request({
    url: '/ab/atlas/review?id='+id,
    method: 'post'
  })
}

// 新增图集
export function addAtlas(data) {
  return request({
    url: '/ab/atlas',
    method: 'post',
    data: data
  })
}

// 修改图集
export function updateAtlas(data) {
  return request({
    url: '/ab/atlas',
    method: 'put',
    data: data
  })
}

// 删除图集
export function delAtlas(id) {
  return request({
    url: '/ab/atlas/' + id,
    method: 'delete'
  })
}

// 导出图集
export function exportAtlas(query) {
  return request({
    url: '/ab/atlas/export',
    method: 'get',
    params: query
  })
}
