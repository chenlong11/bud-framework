$.fn.serializeObject = function() {
    var obj = {};
    var ary = this.serializeArray();
    $.each(ary, function() {
        if (obj[this.name]) {
            if (!obj[this.name].push) {
                obj[this.name] = [obj[this.name]];
            }
            obj[this.name].push(this.value || '');
        } else {
            obj[this.name] = this.value || '';
        }
    });
    return obj;
};