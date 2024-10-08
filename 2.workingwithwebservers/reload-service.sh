#!/bin/bash

sudo systemctl daemon-reload
sudo systemctl start reactjs.service
sudo systemctl status reactjs.service
