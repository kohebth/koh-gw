<div class="row mt-4">
    <div class="col">
        <div class="form-horizontal">
            <div class="form-group row">
                {{html()->label('Username')->class('col-sm-2 form-control-label')->for('username')}}
                <div class="col-sm-10">
                    <input type="text" value="{{$log->operator->username}}" class="form-control bg-white" readonly>
                </div>
            </div>
            <div class="form-group row">
                {{html()->label('Data table')->class('col-sm-2 form-control-label')->for('table_name')}}
                <div class="col-sm-10">
                    <input type="text" value="{{$log->table_name}}" class="form-control bg-white" readonly>
                </div>
            </div>
            <div class="form-group row">
                {{html()->label('Action')->class('col-sm-2 form-control-label')->for('log_type')}}
                <div class="col-sm-10">
                    <input type="text" value="{{$log->log_type}}" class="form-control bg-white" readonly>
                </div>
            </div>
            <div class="form-group row">
                {{html()->label('Create time')->class('col-sm-2 form-control-label')->for('created_at')}}
                <div class="col-sm-10">
                    <input type="text" value="{{$log->created_at}}" class="form-control bg-white" readonly>
                </div>
            </div>

            <div class="form-group row">
                {{html()->label('Data')->class('col-sm-2 form-control-label')->for('data')}}
                <div class="col-sm-10">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>{{ __("Old data") }}</th>
                                <th>{{ __("New data") }}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="fmxw-150">
                                    <div class="text-muted text-pre-wrap"><small>{{json_encode(json_decode($log->old_data, true), JSON_PRETTY_PRINT)}}</small></div>
                                </td>
                                <td class="fmxw-150">
                                    <div class="text-muted text-pre-wrap"><small>{{$log->log_type !== 'delete' ? json_encode(json_decode($log->new_data, true), JSON_PRETTY_PRINT) : 'null' }}</small></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="form-group">
                        <x-buttons.cancel> {{__('Return')}}</x-buttons.cancel>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
