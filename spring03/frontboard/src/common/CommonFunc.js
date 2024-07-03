
export function formatDate(date) {      // 2024-06-20T11:47:12.942238
    var result = date.replace('T', ' ') // T를 공백으로 변경
    var index = result.lastIndexOf(' ') // ' '로 yyyy-mm-dd만 남김

    result = result.substr(0, index);   // 초 뒤로 삭제

    return result;
}