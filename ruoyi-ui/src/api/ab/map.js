import request from '@/utils/request'

// 查询地图菜单列表
export function listMap(query) {
  return request({
    url: '/ab/map/list',
    method: 'get',
    params: query
  })
}

// 查询地图菜单详细
export function getMap(id) {
  return request({
    url: '/ab/map/' + id,
    method: 'get'
  })
}

// 新增地图菜单
export function addMap(data) {
  return request({
    url: '/ab/map',
    method: 'post',
    data: data
  })
}

// 修改地图菜单
export function updateMap(data) {
  return request({
    url: '/ab/map',
    method: 'put',
    data: data
  })
}

// 删除地图菜单
export function delMap(id) {
  return request({
    url: '/ab/map/' + id,
    method: 'delete'
  })
}

// 导出地图菜单
export function exportMap(query) {
  return request({
    url: '/ab/map/export',
    method: 'get',
    params: query
  })
}