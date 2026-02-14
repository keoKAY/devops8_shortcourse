### Another level for this chart (enterprise style 🔥 ):
- Add HorizontalPodAutoscaler
- Add resource limits
- Add readiness/liveness probes
- Add ConfigMap for Spring config
- Add PgBouncer
- Add NetworkPolicy (restrict DB access only to backend)
- Split into subcharts


```bash
kubectl port-forward svc/postgres 5432:5432
helm install fullstack fullstack-demo-chart
helm upgrade fullstack fullstack-demo-chart 
```


### REPO that we can use to test 
- https://github.com/keoKAY/sample-product-reactapp
- https://gitlab.com/devops-trainings3/special-trainning/sample-projects/sample-restful-jpa