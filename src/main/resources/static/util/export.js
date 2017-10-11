
function s2ab(s) {
    if(typeof ArrayBuffer !== 'undefined') {
        var buf = new ArrayBuffer(s.length);
        var view = new Uint8Array(buf);
        for (var i=0; i!=s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
        return buf;
    } else {
        var buf = new Array(s.length);
        for (var i=0; i!=s.length; ++i) buf[i] = s.charCodeAt(i) & 0xFF;
        return buf;
    }
}

function export_table_to_excel(id, type, fn) {
    var wb = XLSX.utils.table_to_book(document.getElementById(id), {sheet:"Sheet JS"});
    var wbout = XLSX.write(wb, {bookType:type, bookSST:true, type: 'binary'});
    if(fn){
        var fname = fn || 'down.' + type;
        try {
            saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream;"}), fname);
        } catch(e) { if(typeof console != 'undefined') console.log(e, wbout); }
    }
    return wbout;
}

function exportXlsByIdHtml5(id, name) {
    name = name + ".xlsx";
    export_table_to_excel(id,'xlsx',name);
}

function exportXlsByIdFlash(id, name) {
    $(".common.export").remove();
    var tableId = $("#export_btn").attr("tableId");
    var fn = $("#export_btn").attr("fn")+".xlsx";

    Downloadify.create('export_btn',{
        swf: '${common_static}/static/plugins/export/media/downloadify.swf',
        downloadImage: '${common_static}/static/plugins/export/images/download.png',
        width: 85,
        height: 24,
        filename: fn, data: function() { var o = export_table_to_excel(tableId, 'xlsx'); return window.btoa(o); },
        transparent: false,
        append: false,
        dataType: 'base64',
        onComplete: function(){ top.layer.alert('保存成功!');},
        onCancel: function(){ },
        onError: function(){ top.layer.alert('出错了!'); }
    });
}