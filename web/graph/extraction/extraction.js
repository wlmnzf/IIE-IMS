/**
 * Created by Liu Tengyu on 2017/5/14.
 */


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
    // var categories = [];
    // for (var i = 0; i < 5; i++) {
    //     categories[i] = {
    //         name: '类目' + i
    //     };
    // }
    var option = {
        title: {
            text: 'Les Miserables',
            subtext: 'Default layout',
            top: 'bottom',
            left: 'right'
        },
        tooltip: {},
        // legend: [{
        //     // selectedMode: 'single',
        //     data: categories.map(function (a) {
        //         return a.name;
        //     })
        // }],

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
                name:"关键人物",
                type: 'graph',
                legendHoverLink: true,
                draggable:true,
                focusNodeAdjacency:true,
                layout: 'force',
                data: nodes.map(function (node, idx) {
                    node.id = idx;
                    return node;
                }),
                edges: links,
                // categories: categories,
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
        url: "/weiboProxy/Knowledge/extraction.do",  //修改修改修改修改
        data:"",
        success:function(d){
            var sourceindex;
            var targetindex;
            var j;
            for(j=0;j<d.links_duty.length;j++){
                var flag1,flag2=0;
                for(var i=0;i<d.nodes.length;i++){
                    nodes[i]={
                        name:d.nodes[i].name,
                        label:{
                            normal: {
                                show: true
                            }
                        },
                        symbolSize: 20
                    };
                    if(d.nodes[i].name===d.links_duty[j].sourcenode){
                        sourceindex=i;
                        flag1=1;
                    }
                    if(d.nodes[i].name===d.links_duty[j].targetnode){
                        targetindex=i;
                        flag2=1;
                    }
                    if(flag1===1&&flag2===1)
                        break;
                }
                links[j]={
                    source:sourceindex,
                    target:targetindex,
                    value:d.links_duty[j].relation
                }
            }
            for(var k=0;k<d.links_execute.length;k++) {
                var flag1, flag2 = 0;
                for (var i = 0; i < d.nodes.length; i++) {
                    if (d.nodes[i].name === d.links_execute[k].sourcenode) {
                        sourceindex = i;
                        flag1 = 1;
                    }
                    if (d.nodes[i].name === d.links_execute[k].targetnode) {
                        targetindex = i;
                        flag2 = 1;
                    }
                    if (flag1 === 1 && flag2 === 1)
                        break;
                }
                links[k+j] = {
                    source: sourceindex,
                    target: targetindex,
                    value:d.links_execute[k].relation
                };
            }
            // console.log("123:"+d.links_duty[0].sourcenode);
            //     for(var i=0;i<d.nodes.length;i++) {
            //         nodes[i] = {
            //             name: d.nodes[i].name,
            //             // value:weight,
            //             label: {
            //                 normal: {
            //                     show: true
            //                 },
            //                 symbolSize: 20
            //             }
            //         };
            //     }
            //     var j;
            //     for(j=0;j<d.links_duty.length;j++){
            //         links[j]={
            //             source:d.links_duty[j].sourcenode,
            //             target:d.links_duty[j].targetnode,
            //             weight:10,
            //             name:d.links_duty[j].relation
            //         }
            //     }
            //     for(var k=j;k<d.links_execute.length;k++){
            //         links[k]={
            //             source:d.links_execute[j].sourcenode,
            //             target:d.links_execute[j].targetnode,
            //             weight:10,
            //             name:d.links_execute[j].relation
            //         }
            //     }
//写links 注意在label中注明关系
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
