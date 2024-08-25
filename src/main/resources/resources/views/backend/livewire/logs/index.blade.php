<div>
    <div class="row mt-4">
        <div class="col">
            <div class="form-group row">
                <div class="col-6">
                    {{ html()->label('Username')->class('form-control-label') }}
                    <div>
                        <select wire:model.defer="filter.username" class="form-control my-2">
                            <option value=''></option>
                            @foreach($admins as $admin)
                                <option value={{ $admin->username }}>{{ $admin->name }} {{ $admin->username }}</option>
                            @endforeach
                        </select>
                    </div>
                </div>

                <div class="col-6">
                    {{ html()->label('Table')->class('form-control-label') }}
                    <div>
                        <select wire:model.defer="filter.table_name" class="form-control my-2 text-capitalize">
                            <option value=''></option>
                            @foreach($tables as $table)
                                <option class="text-capitalize" value={{ $table }}>{{ $table }}</option>
                            @endforeach
                        </select>
                    </div>
                </div>

            </div>
            <div class="form-group">
                <x-buttons.search> {{__('labels.buttons.general.search')}}</x-buttons.search>
                <x-buttons.clear> {{__('labels.buttons.general.clear')}}</x-buttons.clear>
            </div>
            <div class="form-horizontal content-loading">
                <x-loading/>
                <table class="table table-bordered table-hover table-responsive-sm">
                    <thead>
                    <tr>
                        <th>{{__("User")}}</th>
                        <th>{{__("Date")}}</th>
                        <th>{{__("Table")}}</th>
                        <th>{{__("Type")}}</th>
                        <th width="90px">{{ __('labels.backend.action') }}</th>
                    </tr>
                    </thead>
                    <tbody>
                    @if($data->count() > 0)
                    @foreach ($data as $item)
                        <tr>
                            <td>{{$item->operator->username}}</td>
                            <td>{{$item->created_at}}</td>
                            <td>{{$item->table_name}}</td>
                            <td>{{$item->log_type}}</td>
                            <td>
                                @include('backend.includes.action_column.view-action')
                            </td>
                        </tr>
                    @endforeach
                    @else
                        <tr><td colspan="9">{{__("No record")}}</td></tr>
                    @endif
                    </tbody>
                </table>
                @include('backend.includes.action_update_multiple', ['actionBlock' => true])
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-7">
            <div class="float-left">
                {!! $data->total() !!} {{ __('labels.backend.total') }}
            </div>
        </div>
        <div class="col-5">
            <div class="float-right">
                {!! $data->links() !!}
            </div>
        </div>
    </div>
</div>
