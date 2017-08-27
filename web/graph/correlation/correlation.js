

Dash = {};
var nodes=[];
var links=[];

$(document).ready(function() {
    $('#container').width($(window).width());
    window.addEventListener('resize', function () {
        $('#container').width($(window).width());
    });
    // Dash.createChart();
    ajaxCall();
});

Dash.createChart = function() {
    var chart = echarts.init(document.getElementById('graph'));
    var categories = [];
    for (var i = 0; i < 5; i++) {
        categories[i] = {
            name: '类目' + i
        };
    }
    var option = {
        title: {
            text: 'Les Miserables',
            subtext: 'Default layout',
            top: 'bottom',
            left: 'right'
        },
        tooltip: {},
        legend: [{
            // selectedMode: 'single',
            data: categories.map(function (a) {
                return a.name;
            })
        }],

        animationDuration: 1500,
        animationEasingUpdate: 'quinticInOut',
        toolbox: {
            show : true,
            feature : {
                restore : {show: true},
                magicType: {show: true, type: ['force', 'chord']},
                saveAsImage : {show: true}
            }
        },
        series: [
            {
                // width:500,
                // height:500,
                name:"关键词",
                type: 'graph',
                draggable:true,
                focusNodeAdjacency:true,
                legendHoverLink: true,
                layout: 'force',
                data: nodes.map(function (node, idx) {
                    node.id = idx;
                    return node;
                }),
                edges: links,
                categories: categories,
                roam: true,
                label: {
                    normal: {
                        position: 'right',
                        formatter: '{b}'
                    }
                },
                lineStyle: {
                    normal: {
                        color: 'source',
                        curveness: 0.3
                    }
                }
            }
        ]
    };
    chart.setOption(option);
};


function ajaxCall() {
    $.ajax({
        type : 'POST',
        async: true,
        url: "/weiboProxy/Knowledge/correlation.do",  //修改修改修改修改
        data:"",
        success:function(d){
            var sourceindex;
            var targetindex;
            for(var j=0;j<d.links.length;j++){
                var flag1,flag2=0;
                for(var i=0;i<d.nodes.length;i++){
                    nodes[i]={
                        name:d.nodes[i].name,
                        category:parseInt(d.nodes[i].classnum)-1,
                        value:d.nodes[i].weight,
                        label:{
                        normal: {
                            show: d.nodes[i].weight > 0.03
                            }
                        },
                        symbolSize: d.nodes[i].weight*100
                    };

                    if(d.nodes[i].name===d.links[j].sourcenode){
                        sourceindex=i;
                        flag1=1;
                    }
                    if(d.nodes[i].name===d.links[j].targetnode){
                        targetindex=i;
                        flag2=1;
                    }
                    if(flag1===1&&flag2===1)
                        break;
                }
                links[j]={source:sourceindex,
                    target:targetindex,
                    value:d.links[j].value};
            }
            console.log(nodes);
            Dash.createChart(); //关系图
            layer.close(loadindex);
        }
    });
}



function formatNumber(num) {
    if (!/^(\+|-)?(\d+)(\.\d+)?$/.test(num)) {
        return num;
    }
    var a = RegExp.$1, b = RegExp.$2, c = RegExp.$3;
    var re = new RegExp("(\\d)(\\d{3})(,|$)");
    while (re.test(b))   b = b.replace(re, "$1,$2$3");
    return a + "" + b + "" + c;
}
