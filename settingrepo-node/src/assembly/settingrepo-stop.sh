#!/bin/sh
# 程序的根目录
basedir="/usr/local/settingrepo"

PID=$(cat "$basedir/settingrepo.pid")
kill "$PID"
