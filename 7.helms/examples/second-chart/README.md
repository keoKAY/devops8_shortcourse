## Note 
> for working with the second-chart 



* After we have our chart , we can test the syntax with the value by running these command 

```bash 


# 1. run to render to final output 
helm template nginx-release second-chart 



# 2. apply the render configuration 

helm install release-name chart-name 

helm upgrade release-name chart-name --install 
# generate name 
# helm install chart-name --generate
```