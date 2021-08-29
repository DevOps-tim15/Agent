import { Component, OnInit } from '@angular/core';
import { ChartDataSets, ChartType, ChartOptions } from 'chart.js';
import { Label } from 'ng2-charts';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ReportService } from 'src/app/services/report.service';
import { Product, Report } from "./reportDto";

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

  reportList: Map<Product, number> = new Map();

  barChartOptions: ChartOptions = {
    responsive: true,
    scales: {
      yAxes: [{
          ticks: {
              beginAtZero: true
          }
      }]
  }
  };
  barChartLabels: Label[] = [];
  barChartLabelsCount: Label[] = [];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];

  barChartData: ChartDataSets[] = [
    {
      data: [], label: 'The highest product earnings'
    }
  ];

  barChartDataCount: ChartDataSets[] = [
    {
      data: [], label: 'The highest number of products sold'
    }
  ];

  constructor(private reportService: ReportService, private toastr: ToastrService,
    private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getReportList();
    // console.log(this.names)
  }


  getReportList(): void {
    this.reportService.porductEarnings().subscribe(
      repList => {
        Object.entries(repList).forEach(entry => {
          this.barChartLabels.push(entry[0]);
          this.barChartData[0].data.push(Object(entry[1]))

        })
      },
      error => {
        this.toastr.error(error.error);
        console.log(error)
      }
    );
    
    this.reportService.productTotalCount().subscribe(
      repList => {
        Object.entries(repList).forEach(entry => {
          this.barChartLabelsCount.push(entry[0]);
          this.barChartDataCount[0].data.push(Object(entry[1]))

        })
      },
      error => {
        this.toastr.error(error.error);
        console.log(error)
      }
    );

  }
}