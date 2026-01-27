#!/bin/bash

cd  /usr/share/chromium_demo/dist
pwd
python3 serve-python3.py &
chromium --no-sandbox --in-process-gpu http://127.0.0.1:8000/
